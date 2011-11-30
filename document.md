
3203 Networking Project (Assignment 2)
======================================

##Introduction

##Design

##Algorithms

* Prim's Algorithm

* Dijkstra's Algorithm

* Pythagoras and various other Euclidean Geometry forumlas/identities

##Data structures used

In order to represent our system, we needed to form networks of `Sensors`, also known as `Nodes` (the terms are used interchangeably), and these `Nodes` together form a graph or `Network`, with some nodes are connected to each other in a directed way, these are known as our `DirectedNetwork`. Each data structure will be described in more detail.

####Node

The Node, which represents an antenna or sensor in our system must have some information about itself. It needs to know its `SignalStrength` (or radius) (for our purposes, we have given all nodes in the network the same antenna strength).

The node must know its location in the Cartesian plane (i.e. (x, y)), along with its antenna direction and angle if in a directed network. Finally, the Node data structure also maintains a distance to its neighbour if it has been involved in a Shortest Path calculation.

The node may also calculate its `weight` relative to another node. In this case, we're calculating the weight to be the distance between the two nodes, which involves a simple use of `Pythagoras' Theorem`.

!!!!!!!!!!!!
********Computing the anglesss


####Network

A `Network`, as represented in our project, represents a graph of `Nodes` or Sensors, all of which are omni-directional. The network consists of a chosen number of nodes (as chosen by the user in our simulation application), each of which having identical `Signal Strength`, which is also chosen by the user.

Our simulation takes that user input and creates a graph of randomly placed nodes, which are still within range of each other, but in variable places to test our simulation in a number of cases.

The Network data structure is also responsible for connecting neighbouring nodes to one another.

####Directed Network

The Directed Network structure inherits all the properties of the Omni-directional network above, but also allows for more direct (and thus effecient) communication between its nodes.

We make use of *Minimal Spanning Trees* to connect the sensors in an effecient way, arrangin their antennas appropriately so they may communicate in a power-effecient manner.

We make use of Dijkstra's algorithm to help find the shortest path between nodes.

##Implementation

Our project design and algorithms were implemented as a graphical Java application, as we are all familiar with the language and it is easiest to run across multiple platforms. Portability allows us to focus on solving our specific Networking and Routing problems, instead of focussing on cross-platform compatibility issues.

###Class Diagrams

##Testing Results