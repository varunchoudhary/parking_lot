# parking_lot

Parking Lot system for Gojek Inteview .

  Stack Requirements :
        
        java 1.8
        Maven
        for Tests : 
            Junit 4
            Mockito
  
  Compiling and running the unit tests.
  
    steps 1: get in to project root.
    steps 2: run ./bin/setup.sh
  
  Running the Projects.
  
    File based :
        step : run ./bin/parking_lot.sh <input_filepath>
        eg: run ./bin/parking_lot.sh ./file_input.txt
    
    Interactive based: 
        step :  run   ./bin/parking_lot.sh
        eg:  
            cmd1 : create_parking_lot 6
            cmd2 : park KA-01-HH-1234 White
            cmd3 : park KA-01-HH-9999 White
            cmd4 : status
            cmd5 : slot_numbers_for_cars_with_colour White
           