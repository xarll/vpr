mod plot;


use crate::plot::{AddGraph, Mesh, PlotAreaNew, PlotWithLabelMesh};
use plotters::prelude::RGBColor;

const OUT_FILE_NAME: &str = "output/result.png";

fn make_original(x: &[f64]) -> Vec<f64> {
    x.iter().map(|&x| {
        let y = -1.0 / 100.0 * (x - 80.0).powi(2) + 1.0;
        if y < 0.0 {
            0.0
        } else {
            y
        }
    }).collect::<Vec<f64>>()
}

fn make_left_diff_scheme(original_x: &[f64], original_y: &[f64],  hx: f64, u: f64, ht: f64, t: usize) -> Vec<f64> {
    let x_size = original_x.len();
    let t_size = (0..).map(|i| i as f64 * ht).take_while(|&val| val < t as f64 + ht / 2.0).count();
    let mut shape = vec![vec![0.0; x_size]; t_size];
    shape[0].clone_from_slice(original_y);
    for i in 1..t_size {
        for j in 1..x_size - 1 {
            let layer_pre = if u > 0.0 {
                shape[i - 1][j - 1]
            } else {
                shape[i - 1][j + 1]
            };
            shape[i][j] = shape[i - 1][j] - u.abs() * ht * (shape[i - 1][j] - layer_pre) / hx;
        }
    }
    shape[t_size - 1].clone()
}

// fn make_left_diff_scheme(original_x: &[f64], original_y: &[f64], hx: f64, u: f64, ht: f64, t: usize) -> Vec<f64> {
//     let x_size = original_x.len();
//     let mut p= vec![0.0; x_size];
//     let mut p1 = vec![0.0; x_size];
//     p.clone_from_slice(original_y);
//     let mut t_temp = 0.0;
//
//     while t_temp < t as f64 + ht / 2.0 {
//         for i in 1..x_size {
//             p1[i] = p[i] - u * ht * (p[i] - p[i - 1]) / hx;
//         }
//
//         for i in 1..x_size {
//             p[i] = p1[i];
//         }
//         t_temp = t_temp + ht;
//     }
//     p1
// }

fn make_center_diff_scheme(x: &[f64], original_y: &[f64],  hx: f64, u: f64, ht: f64, t: usize) -> Vec<f64> {
    let x_size = x.len();
    let t_size = (0..).map(|i| i as f64 * ht).take_while(|&val| val < t as f64 + ht / 2.0).count();
    let mut shape = vec![vec![0.0; x_size]; t_size];
    shape[0].clone_from_slice(&original_y);
    for i in 1..t_size {
        for j in 1..x_size - 1 {
            shape[i][j] = shape[i - 1][j] - 0.5 * u * ht * (shape[i - 1][j + 1] - shape[i - 1][j - 1]) / hx;
        }
    }
    shape[t_size - 1].clone()
}

fn make_kabare_scheme(x: &[f64], original_y: &[f64], hx: f64, u: f64, ht: f64, t: usize) -> Vec<f64> {
    let x_size = x.len();
    let t_size = (0..).map(|i| i as f64 * ht).take_while(|&val| val < t as f64 + ht / 2.0).count();
    let mut shape = vec![vec![0.0; x_size]; t_size];
    shape[0].clone_from_slice(&original_y);
    shape[1].clone_from_slice(&original_y);
    for i in 2..t_size {
        for j in 1..(x_size - 1) {
            if u > 0.0 {
                shape[i][j] = shape[i - 1][j] - (shape[i - 1][j - 1] - shape[i - 2][j - 1]) - 2.0 * u.abs() * ht * (shape[i - 1][j] - shape[i - 1][j - 1]) / hx;
            } else {
                shape[i][j] = shape[i - 1][j] - (shape[i - 1][j + 1] - shape[i - 2][j + 1]) - 2.0 * u.abs() * ht * (shape[i - 1][j] - shape[i - 1][j + 1]) / hx;
            }
        }
    }
    shape[t_size - 1].clone()
}

fn combine(x: &[f64], original_y: &[f64],  hx: f64, u: f64, ht: f64, t: usize) -> Vec<f64> {
    let x_size = x.len();
    let t_size = (0..).map(|i| i as f64 * ht).take_while(|&val| val < t as f64 + ht / 2.0).count();
    let mut shape = vec![vec![0.0; x_size]; t_size];
    shape[0].clone_from_slice(original_y);
    shape[1].clone_from_slice(original_y);
    for i in 2..t_size {
        for j in 1..(x_size - 1) {
            if u > 0.0 {
                shape[i][j] = shape[i - 1][j] - 0.5 * (shape[i - 1][j - 1] - shape[i - 2][j - 1]) - 0.25 * u.abs() * ht * (shape[i - 1][j + 1] + 4.0 * shape[i - 1][j] - 5.0 * shape[i - 1][j - 1]) / hx
            } else {
                shape[i][j] = shape[i - 1][j] - 0.5 * (shape[i - 1][j + 1] - shape[i - 2][j + 1]) - 0.25 * u.abs() * ht * (shape[i - 1][j - 1] + 4.0 * shape[i - 1][j] - 5.0 * shape[i - 1][j + 1]) / hx;
            }
        }
    }
    shape[t_size - 1].clone()
}


fn main() {
    const HX: f64 = 1.0;
    const HT: f64 = 0.1;
    const X: usize = 100;
    const T: usize = 100;
    const U: f64 = -0.5;

    let mut arena = plot::PlotArea::new(Some("Задача переноса веществ".to_string()));
    let x: Vec<f64> = (0..)
        .map(|i| i as f64 * HX)
        .take_while(|&val| val < X as f64)
        .collect();

    let original_y = make_original(&x);

    let q1 = make_left_diff_scheme(&x, &original_y,  HX, U, HT, T);
    let q2 = make_center_diff_scheme(&x, &original_y,  HX, U, HT, T);
    let q3 = make_kabare_scheme(&x, &original_y,  HX, U, HT, T);
    let q4 = combine(&x, &original_y, HX, U, HT, T);

    let plot1 = arena.plot("Правый уголок".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot1, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot1, &q1, &x, RGBColor(255, 0, 0), "Левая разностная схема");

    let plot2 = arena.plot("Центральная разностная схема".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot2, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot2, &q2, &x, RGBColor(0, 155, 0), "Центральная разностная схема");

    let plot3 = arena.plot("Схема «кабаре»".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot3, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot3, &q3, &x, RGBColor(0, 0, 255), "Схема «кабаре»");

    let plot4 = arena.plot("Комбинация «кабаре» и центральной схем".to_string(), Mesh { x: 5, y: 5 });
    AddGraph::graph(plot4, &original_y, &x, RGBColor(0, 0, 0), "Исходная функция");
    AddGraph::graph(plot4, &q4, &x, RGBColor(200, 100, 0), "Комбинация");

    arena.build(OUT_FILE_NAME);

}
