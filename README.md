# Multithreaded Parking System Simulation

## Project Overview
This project is a simulation of a parking system with limited spots, multiple entry gates, and cars arriving at specific times. It was developed as part of an Operating Systems assignment, focusing on thread synchronization using semaphores in Java. The simulation manages concurrent arrivals and departures of cars using threading techniques.

The parking system has:
- 4 parking spots.
- 3 gates (Gate 1, Gate 2, Gate 3), each receiving cars at different times.
- Each car is represented by a thread that tries to enter the parking lot, stays for a predetermined duration, and then exits.

## Features
1. **Thread Synchronization**:
   - Manages parking spots availability using semaphores.
   - Each car's thread attempts to acquire a parking spot, stays for its duration, and then releases the spot.

2. **Concurrency Management**:
   - Handles multiple car arrivals at different gates without race conditions.

3. **Real-Time Simulation**:
   - Cars arrive at specific times, and the parking system reflects this accurately using `sleep()` to simulate arrival and stay times.

4. **Logging and Reporting**:
   - Logs each car’s activity (arrival, parking, and departure).
   - Reports the total number of cars parked and the total served during the simulation.

## Requirements
- JDK 11 or higher
- IntelliJ IDEA or any other Java IDE

