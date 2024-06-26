file_path = 'c:/Users/zxckj/Documents/Showcase-1/Data/NY-region.tmg'

import math

def parse_tmg_file(file_path):
    nodes = {}
    edges = []
    with open(file_path, 'r') as file:
        lines = file.readlines()
        num_nodes, num_edges = map(int, lines[1:3])
        # Parsing nodes
        for i in range(3, 3 + num_nodes):
            parts = lines[i].strip().split()
            node_id = parts[0]
            x, y = map(float, parts[1:3])  
            nodes[node_id] = (x, y)

        for i in range(3 + num_nodes, 3 + num_nodes + num_edges):
            parts = lines[i].strip().split()
            edge_from, edge_to = parts[:2]  
            edges.append((edge_from, edge_to))
    return nodes, edges

def create_graph(nodes, edges):
    graph = {node: [] for node in nodes}
    for edge in edges:
        edge_from, edge_to = edge
        distance = math.sqrt((nodes[edge_from][0] - nodes[edge_to][0]) ** 2 + (nodes[edge_from][1] - nodes[edge_to][1]) ** 2)
        graph[edge_from].append((edge_to, distance))
        graph[edge_to].append((edge_from, distance)) 
    return graph
