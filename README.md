# air-travel-filtering

## Description of the project:

Test task for writing filters for air travel. Excludes from the test set flights based on the recommendations of the rules (each rule requires a separate output of the list of flights):

1. Departure before available times
2. Available segments with an arrival date earlier than the departure date
3. total time spent on the ground two hours (time on the ground is the interval between arrival of one segment and departure for such one)

## Test Task

[Test task](https://github.com/Don1ns/air-travel-filtering/wiki/Test-Task)

## Author

- Riyaz Karimullin | [Don1ns](https://github.com/Don1ns)

## Technology stack

Backend:
  - Java 17
  - Maven
  - JUnit
  - Stream API
