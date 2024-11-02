# Border Crossing Simulation 🚛🚗🚌

This project simulates a border crossing with multiple terminals for police and customs control. The system includes various types of vehicles, passengers, and procedural rules to mimic a real-world border crossing scenario.

## Simulation Overview

The border crossing operates with:
- **3 Police Terminals**
- **2 Customs Terminals**

Only one side of the border is simulated, with vehicles proceeding through police control first, followed by customs.

### Vehicle Types
1. **Personal Vehicles**: Up to 5 passengers (including the driver).
2. **Buses**: Capacity for up to 52 passengers (including the driver).
3. **Trucks**: Up to 3 passengers (including the driver), with potential cargo requiring customs documentation.

### Passenger and Cargo Details
- **Trucks**: 
  - 50% chance of requiring customs documentation.
  - Declared vs. Actual Cargo Weight: In 20% of trucks, the actual weight is up to 30% more than declared.
  
- **Buses**: 
  - 70% chance each passenger has a suitcase in the luggage compartment.
  - 10% of suitcases may contain prohibited items.
  
- **Passenger Identification**: Each passenger has an ID document.
  - 3% of all documents are invalid, preventing those passengers from crossing.

### Initial Setup
- **Queue Initialization**:
  - A queue of 50 vehicles is created: 5 buses, 10 trucks, and 35 personal vehicles, all in random positions.

## Processing Flow

1. **Police Check**:
   - Vehicles are processed in parallel across three police terminals.
   - One police terminal (far right in the graphical view) is exclusively reserved for trucks.
   - Other vehicles are processed at the remaining two terminals.
   
2. **Customs Check**:
   - Processed vehicles wait for the shared customs terminal if the police terminals finish earlier.
   - **Processing Times**:
     - **Personal Vehicles & Trucks**: 0.5 seconds per passenger at each terminal.
     - **Buses**: 0.1 seconds per passenger.
     
3. **Incident Handling**:
   - Invalid documents: Recorded in a binary serialized format for sanctioned individuals.
   - Vehicles continue processing without sanctioned passengers unless the driver is affected.
   - Customs Rejections:
     - Overloaded trucks and prohibited items in suitcases prevent crossing.
     - Rejections are logged in a text format.

## User Interface and Display

- **Queue Visualization**: Shows the next five vehicles in line to cross. The remaining queue is displayed in a separate UI.
- **Incident Overview**: Displays vehicles involved in incidents, with exact details.
- **Vehicle Data**: Provides details on vehicles and passengers, accessible by clicking the vehicle or entering the vehicle ID.
- **Simulation Events**: All events are logged and displayed in real-time.

## Additional Features

- **Terminal Availability**: Terminals can be blocked or re-enabled based on a shared file.
- **Simulation Control**: Start, stop, and reset options available, with a timer displaying the simulation duration.
- **Exception Handling**: Uses a `Logger` class for error handling across all classes.

### Color Coding and Icons
Each vehicle type is represented with specific colors or icons for easy identification.

---

## Implementation Details

- Vehicle movement logic is implemented within the vehicle classes.
- The simulation uses a graphical interface to show all relevant events.
