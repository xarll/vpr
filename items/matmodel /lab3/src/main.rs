mod plot;

use std::f64::consts::PI;
use crate::plot::{AddGraph, Mesh, PlotAreaNew, PlotWithLabelMesh};
use plotters::prelude::RGBColor;

const OUT_FILE_NAME: &str = "output/result.png";

fn make_original(x: &[f64]) -> Vec<f64> {
    x.iter().map(|&x| {
        if 50.0 <= x && x <= 60.0 {
            (x - 50.0) / 10.0
        } else if 60.0 < x && x <= 70.0 {
            -(x - 60.0).powi(2)/ 200.0 + 1.0
        } else { 0.0 }
    }).collect::<Vec<f64>>()
}

fn make_explicit_method(original_x: &[f64], original_y: &[f64], hx: f64, a: f64, ht: f64, t: usize) -> Vec<f64> {
    let x_size = original_x.len();
    let t_size = (0..).map(|i| i as f64 * ht).take_while(|&val| val < t as f64 + ht / 2.0).count();
    let mut shape = vec![vec![0.0; x_size]; t_size];
    shape[0].clone_from_slice(original_y);
    for i in 0..t_size - 1 {
        for j in 1..x_size - 1 {
            shape[i + 1][j] = shape[i][j] + a * ht / hx.powi(2) * (shape[i][j - 1] - 2.0 * shape[i][j] + shape[i][j + 1]);
        }
    }
    shape[t_size - 1].clone()
}

fn solve_tridiagonal(a: f64, b: f64, c: f64, d: &Vec<f64>) -> Vec<f64> {
    let n = d.len();
    let mut alpha = vec![0.0; n];
    let mut beta = vec![0.0; n];
    let mut x = vec![0.0; n];

    alpha[0] = b;
    beta[0] = d[0];

    for i in 1..n {
        let w = a / alpha[i - 1];
        alpha[i] = b - w * c;
        beta[i] = d[i] - w * beta[i - 1];
    }

    x[n - 1] = beta[n - 1] / alpha[n - 1];
    for i in (0..n - 1).rev() {
        x[i] = (beta[i] - c * x[i + 1]) / alpha[i];
    }

    x
}

fn make_implicit_method(x: &[f64], original_y: &[f64], hx: f64, a: f64, ht: f64, t: usize) -> Vec<f64> {
    let x_size = x.len();
    let t_size = (0..).map(|i| i as f64 * ht).take_while(|&val| val < t as f64 + ht / 2.0).count();
    let mut shape = vec![vec![0.0; x_size]; t_size];
    shape[0].clone_from_slice(&original_y);

    let alpha = a * ht / (2.0 * hx.powi(2));
    for i in 0..t_size - 1 {
        let mut d = vec![0.0; x_size - 2];

        for j in 1..x_size - 1 {
            d[j - 1] = shape[i][j] + alpha * (shape[i][j - 1] - 2.0 * shape[i][j] + shape[i][j + 1]);
        }

        let solution = solve_tridiagonal(-alpha, 1.0 + 2.0 * alpha, -alpha, &d);
        for j in 1..x_size - 1 {
            shape[i + 1][j] = solution[j - 1];
        }
    }

    shape[t_size - 1].clone()
}

fn make_analytical_solution(
    x: &[f64],
    original_y: &[f64],
    hx: f64,
    a: f64,
    t: usize,
    x_scale: usize
) -> Vec<f64> {
    let num_terms = 100;
    let l = x_scale as f64;
    let mut result = vec![0.0; x.len()];

    for n in 1..=num_terms {
        let n_pi_over_l = (n as f64) * PI / l;

        let mut b_n = 0.0;
        for i in 0..(x.len() - 1) {
            let avg = (original_y[i] * (n_pi_over_l * x[i]).sin() + original_y[i + 1] * (n_pi_over_l * x[i + 1]).sin()) / 2.0;
            b_n += avg * hx;
        }
        b_n *= 2.0 / l;

        for (i, &x_i) in x.iter().enumerate() {
            result[i] += b_n * (-n_pi_over_l.powi(2) * a * t as f64).exp() * (n_pi_over_l * x_i).sin();
        }
    }
    result
}


fn main() {
    const HX: f64 = 1.0;
    const HT: f64 = 0.1;
    const X: usize = 100;
    const T: usize = 50;
    const A: f64 = 1.0;

    let mut arena = plot::PlotArea::new(Some("Задача теплопроводности".to_string()));
    let x: Vec<f64> = (0..)
        .map(|i| i as f64 * HX)
        .take_while(|&val| val < X as f64)
        .collect();

    let original_y = make_original(&x);

    let q1 = make_explicit_method(&x, &original_y,  HX, A, HT, T);
    let q2 = make_implicit_method(&x, &original_y,  HX, A, HT, T);
    let analytical = make_analytical_solution(&x, &original_y, HX, A, T, X);

    let plot1 = arena.plot("Явная схема".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot1, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot1, &q1, &x, RGBColor(255, 0, 0), "Явная схема");

    let plot2 = arena.plot("Неявная схема".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot2, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot2, &q2, &x, RGBColor(0, 0, 255), "Неявная схема");

    let plot3 = arena.plot("Аналитическое решение".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot3, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot3, &analytical, &x, RGBColor(0, 255, 0), "Аналитическое решение");

    arena.build(OUT_FILE_NAME);

}
