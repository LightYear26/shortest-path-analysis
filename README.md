# Shortest Path Analysis

## Project Overview

**Analysis of Pros and Cons of Dijkstra's and Bellman-Ford Using a Graph Generator**

This project explores the performance and efficiency of Dijkstra's and Bellman-Ford shortest-path algorithms. By implementing a random graph generator, we analyze the algorithms under various conditions, providing insights into their strengths, limitations, and optimal use cases.

## Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Results](#results)
- [Acknowledgements](#acknowledgements)

## About the Project

### Objective

The main goal is to evaluate Dijkstra’s and Bellman-Ford algorithms using a **custom graph generator**. This generator allows for creating graphs with adjustable parameters (such as node count, edge density, and edge weights), enabling flexible and controlled simulation of various scenarios.

### Why a Graph Generator?

Using a graph generator allows for:
- **Customizable scenarios** with varying sizes, densities, and weights.
- **Automation** for generating multiple graph instances efficiently.
- **Scalability** to test performance on both small and large graph sizes.
- **Objective analysis** without dependency on pre-existing datasets.

## Features

- **Random Graph Generation:** Create graphs with adjustable parameters.
- **Algorithm Comparison:** Compare Dijkstra's and Bellman-Ford algorithms directly.
- **Execution Metrics:** Measure and log runtime, efficiency, and performance in different scenarios.
- **Visualization:** Visualize generated graphs and shortest-path results for deeper insights.

## Getting Started

### Installation

1. **Clone the Repository:**

   To get started, clone the repository to your local machine using the following command:
   ```bash
   git clone https://github.com/username/shortest-path-analysis.git

   ## Usage

### Configure Graph Parameters:
- You can adjust the number of nodes, edge density, and edge weights in the `GraphGenerator.java` script.
- Modify the script to generate a random graph with the desired parameters.

### Select Algorithm to Run:
- The program allows you to choose either **Dijkstra's Algorithm** or **Bellman-Ford Algorithm**. Both algorithms will be executed on the generated graph.
- You can set the starting node for the shortest-path calculation.

### View Results:
After the algorithms have run, the program displays:
- The shortest path calculated by both algorithms.
- The time taken by each algorithm to process the graph.
- A comparison of results.

### Visualize the Graph:
- The program will visualize the generated graph and highlight the shortest paths calculated by both algorithms.

## Results

This project allows a direct comparison between **Dijkstra's Algorithm** and **Bellman-Ford Algorithm** under varying conditions:
- **Dijkstra's Algorithm**: Suitable for graphs with non-negative weights, generally faster with dense graphs.
- **Bellman-Ford Algorithm**: Handles graphs with negative edge weights but may take longer to execute compared to Dijkstra's for large graphs.

## Acknowledgements

Developed by **Pradnesh Fernandez A**, IIIT Dharwad.

- **Author**: Pradnesh Fernandez A
- **Roll Number**: 23BDS044

Required packages listed in `requirements.txt`.

