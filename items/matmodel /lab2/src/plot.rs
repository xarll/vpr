use plotters::prelude::{BitMapBackend, ChartBuilder, Color, DiscreteRanged, IntoDrawingArea, IntoLinspace, BLACK};
use plotters::style::{RGBColor, WHITE};
use std::cmp::Ordering;
use std::ops::Range;
use plotters::element::PathElement;

fn partial_min(a: f64, b: f64) -> f64 {
    match a.partial_cmp(&b).unwrap_or(Ordering::Equal) {
        Ordering::Less | Ordering::Equal => a,
        Ordering::Greater => b,
    }
}

fn partial_max(a: f64, b: f64) -> f64 {
    match a.partial_cmp(&b).unwrap_or(Ordering::Equal) {
        Ordering::Greater | Ordering::Equal => a,
        Ordering::Less => b,
    }
}


pub trait PlotAreaNewWithSize {
    #[allow(unused)]
    fn new(title: Option<String>, w: u32, h: u32) -> Self;
}

pub trait PlotAreaNew {
    #[allow(unused)]
    fn new(title: Option<String>) -> Self;
}

pub trait AddGraphFunction {
    #[allow(unused)]
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64);
}

pub trait AddGraphFunctionWithColor {
    #[allow(unused)]
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64, color: RGBColor);
}

pub trait AddGraphFunctionWithLabel {
    #[allow(unused)]
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64, label: &str);
}

pub trait AddGraphFunctionWithColorLabel {
    #[allow(unused)]
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64, color: RGBColor, label: &str);
}

pub trait AddGraph {
    #[allow(unused)]
    fn graph(&mut self, y: &[f64], x: &[f64], color: RGBColor, label: &str);
}

pub trait PlotWithLabelMesh {
    #[allow(unused)]
    fn plot(&mut self, label: String, mesh: Mesh) -> &mut Plot;
}

pub trait PlotWithLabelMeshSize {
    #[allow(unused)]
    fn plot(&mut self, label: String, mesh: Mesh, x_max_min: (f64, f64), y_max_min: (f64, f64)) -> &mut Plot;
}

pub struct Plot {
    label: Option<String>,
    mesh: Option<Mesh>,
    x_max_min: Option<(f64, f64)>,
    y_max_min: Option<(f64, f64)>,
    graphs: Vec<Graph>,
}

enum Graph {
    Function(fn(f64) -> f64, Range<f64>, f64, RGBColor, String),
    Data(Vec<f64>, Vec<f64>, RGBColor, String),
}

pub struct Mesh {
    pub x: usize,
    pub y: usize,
}

pub struct PlotArea {
    w: u32,
    h: u32,
    background: RGBColor,
    title_font: (String, u32),
    title: Option<String>,
    items: Vec<Plot>,
}

impl PlotAreaNewWithSize for PlotArea {
    fn new(title: Option<String>, w: u32, h: u32) -> Self {
        Self {
            w,
            h,
            background: RGBColor(255, 255, 255),
            title_font: ("sans-serif".to_string(), 60),
            title,
            items: Vec::new(),
        }
    }

}

impl PlotAreaNew for PlotArea {
    fn new(title: Option<String>) -> Self {
        Self {
            w: 1024,
            h: 768,
            background: RGBColor(255, 255, 255),
            title_font: ("sans-serif".to_string(), 60),
            title,
            items: Vec::new(),
        }
    }
}

impl PlotWithLabelMesh for PlotArea {
    fn plot(&mut self, label: String, mesh: Mesh) -> &mut Plot {
        self.items.push(Plot {
            label: Some(label),
            mesh: Some(mesh),
            x_max_min: None,
            y_max_min: None,
            graphs: Vec::new(),
        });
        self.items.last_mut().unwrap()
    }
}

impl PlotWithLabelMeshSize for PlotArea {
    fn plot(&mut self, label: String, mesh: Mesh, x_max_min: (f64, f64), y_max_min: (f64, f64)) -> &mut Plot {
        self.items.push(Plot {
            label: Some(label),
            mesh: Some(mesh),
            x_max_min: Some(x_max_min),
            y_max_min: Some(y_max_min),
            graphs: Vec::new(),
        });
        self.items.last_mut().unwrap()
    }
}



impl AddGraphFunction for Plot {
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64) {
        self.graphs.push(Graph::Function(y, x, step, RGBColor(0, 0, 0), "".to_string()));
    }
}

impl AddGraphFunctionWithColor for Plot {
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64, color: RGBColor) {
        self.graphs.push(Graph::Function(y, x, step, color, "".to_string()));
    }
}

impl AddGraphFunctionWithLabel for Plot {
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64, label: &str) {
        self.graphs.push(Graph::Function(y, x, step, RGBColor(0, 0, 0), label.to_string()));
    }
}

impl AddGraphFunctionWithColorLabel for Plot {
    fn graph(&mut self, y: fn(f64) -> f64, x: Range<f64>, step: f64, color: RGBColor, label: &str) {
        self.graphs.push(Graph::Function(
            y,
            x,
            step,
            color,
            label.to_string()
        ));
    }
}

impl AddGraph for Plot {
    fn graph(&mut self, y: &[f64], x: &[f64], color: RGBColor, label: &str) {
        self.graphs.push(Graph::Data(y.to_vec(), x.to_vec(), color, label.to_string()));
    }
}

impl PlotArea {
    pub fn build(&self, out_file_name: &str) {
        let root_area = BitMapBackend::new(
            out_file_name,
            (self.w, self.h)
        ).into_drawing_area();
        root_area.fill(&self.background).unwrap();
        let root_area = root_area.titled(
            self.title.as_ref().unwrap().as_str(),
            (self.title_font.0.as_str(), self.title_font.1),
        ).unwrap();

        if self.items.len() == 0 {
            return;
        }

        let items_count = self.items.len();
        let net = if items_count == 1 {
            (1, 1)
        } else if items_count == 2 {
            (1, 2)
        } else if items_count % 2 == 0 {
            (items_count / 2, 2)
        } else {
            ((items_count + 1) / 2, 2)
        };

        let arenas = root_area.split_evenly((net.0, net.1));
        let mut arenas_iterator = arenas.iter();
        let mut plots_iterator = self.items.iter();
        for _ in 0..net.0 {
            for _ in 0..net.1 {
                let arena = arenas_iterator.next().unwrap();
                if let Some(item) = plots_iterator.next() {
                    let graphs = item.graphs.iter().map(|graph| match graph {
                        Graph::Function(y, range, step, color, label) => {
                            let (x, y) = range.clone()
                                .step(step.clone())
                                .values()
                                .map(|x| (x, y(x)))
                                .unzip();
                            (y, x, color, label)
                        }
                        Graph::Data(y, x, color, label) => {
                            (y.clone(), x.clone(), color, label)
                        }
                    }).collect::<Vec<_>>();

                    let mut x_min = f64::MAX;
                    let mut x_max = f64::MIN;
                    let mut y_min = f64::MAX;
                    let mut y_max = f64::MIN;

                    for (y, x, _, _) in graphs.clone() {
                        if y.len() != x.len() {
                            panic!("The length of x and y must be the same");
                        }
                        if y.len() == 0 {
                            continue;
                        }

                        x_min = partial_min(*x.iter().min_by(|a, b| a.partial_cmp(b).unwrap()).unwrap(), x_min);
                        x_max = partial_max(*x.iter().max_by(|a, b| a.partial_cmp(b).unwrap()).unwrap(), x_max);
                        y_min = partial_min(*y.iter().min_by(|a, b| a.partial_cmp(b).unwrap()).unwrap(), y_min);
                        y_max = partial_max(*y.iter().max_by(|a, b| a.partial_cmp(b).unwrap()).unwrap(), y_max);
                    }

                    if let Some((opt_x_max, opt_x_min)) = item.x_max_min {
                        x_max = opt_x_max;
                        x_min = opt_x_min;
                    }

                    if let Some((opt_y_max, opt_y_min)) = item.y_max_min {
                        y_max = opt_y_max;
                        y_min = opt_y_min;
                    }

                    let mut cc = ChartBuilder::on(&arena)
                        .margin(5)
                        .set_all_label_area_size(50)
                        .caption(item.label.clone().map_or(
                            "".to_string(),
                            |s| s
                        ), ("sans-serif", 20))
                        .build_cartesian_2d(x_min..x_max, y_min..y_max).unwrap();


                    for (y, x, color, label) in graphs {
                        if y.len() != x.len() {
                            panic!("The length of x and y must be the same");
                        }
                        if y.len() == 0 {
                            continue;
                        }

                        cc.draw_series(plotters::prelude::LineSeries::new(
                            x.iter().zip(y.iter()).map(|(x, y)| (*x, *y)),
                            color,
                        )).unwrap().label(label).legend(move |(x, y)| {
                            PathElement::new(vec![(x, y), (x + 20, y)], color)
                        });
                    }
                    if let Some(ref mesh) = item.mesh {
                        cc.configure_mesh()
                            .x_labels(mesh.x)
                            .y_labels(mesh.y)
                            .draw().unwrap();
                    }

                    cc.configure_series_labels()
                        .background_style(&WHITE.mix(0.8))
                        .border_style(&BLACK)
                        .draw().unwrap();

                    cc.configure_series_labels()
                        .draw().unwrap();
                }
            }
        }

        root_area.present().unwrap();
    }
}