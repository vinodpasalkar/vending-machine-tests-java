# Task

### Write a program to design Vending Machine using your 'favourite language' with all possible tests

1. Accept coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.

2. Allow user to select products Coke(25), Pepsi(35), Soda(45)

3. Allow user to take refund by cancelling the request.

4. Return selected product and remaining change if any.

5. Allow reset operation for vending machine supplier.

### Code Structure

#### Main Classes, Interface
1. VendingMachineAPI - an interface which defines the API of Vending Machine

2. VendingMachineImpl - Implements the VendingMachineAPI

3. CoinInventory - Class to perform operation related to cash such as 
adding cash to vending machine, calculating balance, 
deducting balance when an item is bought etc.,

4. ItemInventory - Class to perform operations such as 
adding items to the vending machine and 
decrementing the item count when an item is bought

#### Set up instructions
1. Clone the repo from the link below
2. Install IntelliJ idea and open the project into the same
3. Context click ( Right click ) on the vendingMAchine folder under below path
   src/test/java/com/vendingMachine
4. Click on Run Tests in vendingMachine and see the results in the Run window

#### Additional test cases to be considered
1. power outage during transaction [placing an oder] : mark transaction as null
2. verify performance of the order ( processing time)
3. Shaking machine does not dispense product
4. Wrong product selected via typo
5. Discount on selected products ( expiry date near)
6. Look for temperature api for optimum the temperature ( negative temperature etc)
7. 

                       
