
# Ticket-Service

# Author: Prabin Adhikari

# Assumption: 
1. Venue has nXm number of seats, where n is number of row and m is number of seats per row.
2. Seats are numbered from first row and seats from front row will be provided as first come first serve basis
3. Customer can hold and reserve single or group of seats.
4. Seat hold time out is set to one second.
5. From requirement I understood, email Id of customer is sufficient to hold or reserve seat so seperate Customer object is not created.
6. If user is trying to hold or reserve more than available seats then, message with available number of seats is provided

# Class Diagram:
![alt text](/ClassDiagram.png)
# How to build and run : 
These steps are required to be executed at root directory of project (where ***pom.xml*** exists).

1. Clone the project at your favorite directory and use ***mvn clean install*** command to build modules an install in local repository.
2. To run all test use ***mvn test*** command. 

# Notes: 

1. Since it is high-demand performance venue, to avoid holding of same seat/s by multiple threads block level synchronization is used. 
2. Two second hold time will be  observed while running TicketServiceImpl test. This time is used to test hold time expiration. 
3. I was thinking to introduce a VenueServiceInterface but assuming this is an exercise having single venue I did not make that. 

# Git-Hub related Note:
1. I noticed my commits have two names *Prabin Adhikari* and *prabinadhikari24* 
 *prabinadhikari24* is my GitHub username which is with email *adhikari.prabin10@gmail.com* . Since I did not set my email for couple of commits at the begnning, it  is showing *Prabin Adhikari* instead *prabinadhikari24.*
2. I changed my profile picture after some commits hence those commits doesnt display my photo. Today, I noticed that github doesnot updates recent picture to old commits.
