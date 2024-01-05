# Project Portunus

Project Portunus is a car hacking tool that allows the user to control certain car features from your phone. These features include locking and unlocking the car as well as gaining the location of the car itself. This eliminates the use of the car key as your phone is capable of doing everything the car remote can be used for and more. This idea was Allthenticate's, our sponsor, who wanted to incorporate all login information including vehicle access all from your phone. This would allow our phone to be used as a key for everything.

<h2> Step 1: CAN Bus </h2>
By connecting to the car's on-board-diagnostics (OBDII) port, we are able to read the codes that car's components use to communicate. Every Electronic sensor or actuator of the car send messages through the CAN bus. However, it is not easy to access on many cars.

<h2> Step 2: GPS Module </h2>
The GPS module uploads real-time location data to your phone using the Global System for Mobile Communications technology. This data is stored in the cloud and the user can easily monitor it.

<h2> Step 3: PCB Design </h2>
Using Autodesk EAGLE software our team has designed a custom board that sits on top of the Raspberry Pi Zero. The PCB hat has the necessary CAN bus transceivers, pinouts and power supply. Car's OBDII port will plug into the 9-pin connector on the board.

<h2> Step 4: App Integration </h2>
The Raspberry Pi Zero will be in communication with a Firebase Realtime Database, storing hierarchal data in the form of a JSON tree. From the Realtime Database, the mobile application will query data that is being stored and display it to the user in a useful format, allowing the user to organize vehicle data the way they desire.

<p></p>
https://sites.google.com/view/projectportunus/home

