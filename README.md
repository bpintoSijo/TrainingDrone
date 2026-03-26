# TrainingDrone
Java training exercice 

# Java Exercise — Exploration Drones on Titan

A fleet of autonomous exploration drones is to be deployed by a space agency on a plateau located on Titan, Saturn’s largest moon.

This plateau, which is perfectly rectangular, must be navigated by the drones so that their onboard sensors can scan and map the entire surrounding terrain and send data back to the orbital station.

## Representation

A drone’s position is defined by:
- **x** and **y** coordinates
- A direction among:
  - **N** (North)
  - **E** (East)
  - **S** (South)
  - **W** (West)

The plateau is divided into a grid.

**Example:**
```
2 1 E
```
The drone is at position (2,1) facing East.

---

## Movement Instructions

The possible commands are:
- **L**: rotate 90° to the left
- **R**: rotate 90° to the right
- **F**: move forward one grid point

### Rules:
- Rotation does not change position
- Moving forward does not change orientation
- Coordinate system:
  - North → (x, y+1)
  - East → (x+1, y)
  - South → (x, y-1)
  - West → (x-1, y)

---

## Input

- The first line: upper-right coordinates of the plateau
- The lower-left corner is assumed to be **(0,0)**

Each drone is defined by 2 lines:
1. Initial position (**x y direction**)
2. Movement instructions (string of commands)

Drones are executed **sequentially**.

---

## Output

For each drone, print:
- Final position
- Final orientation

---

## Example

### Input
```
6 6
2 1 E
FFLFFRFF
0 3 N
FFRFFLFFF
```

### Expected Output
```
4 3 E
2 6 N
```

---

## Execution

The program must be executable using:
```
java -jar drone.jar input.txt
```

---

## Constraints / Goals

- Handle grid boundaries correctly
- Properly parse the input file
- Model orientation cleanly (using an `enum` is recommended)
- Process multiple drones sequentially
- Write clean, modular, and testable code
```
