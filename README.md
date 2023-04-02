# picking scheduler

Application created to automate and optimize schedule for picking grocery in shops.

## how to use this program
1) clone this repository
2) using terminal, navigate to /Scheduler/target
3) type: java -jar Scheduler-1.0-SNAPSHOT-jar-with-dependencies.jar {arg1} {arg2}

## run arguments:
arg1 - .json file containing shop configuraion with all pickers and start/stop picking hours

arg1 .json required fields:
1) pickers -> string array of pickers IDs
2) pickingStartTime - LocalTime
3) pickingEndTime - LocalTime


arg2 - .json file contatining array of orders objects that we want to process

arg2 object required fileds:
1) orderId - String
2) orderValue - BigDecimal
3) pickingTime - Duration
4) completeBy - Localtime


## program output

Result of program work is written to standard output and shows prepared schedule for pickers that will maximize numbers of orders that will be processed

example: P1 order-1 09:00
P1 - pickers ID
order-1 - orderID
09:00 - start picking time prepared for that picker

