import math
##
# Computing billing charge for customer who has rented a car
# Tyler Roberts, January 18, 2016
#

# customer inputs
customer_name = str(input("Please enter your name: "))
customer_name = customer_name.upper()
classification_code = input("Enter classification code(B for budget, D for Daily or W for weekly): ")
classification_code = classification_code.upper()
if classification_code != "B" and classification_code != "D" and classification_code != "W":
    print()
    print("INCORRECT CLASSIFICATION CODE: ")
    print(classification_code, "is not valid. Please enter a valid classification code.")
    exit()
days_rented = int(input("Enter the amount of days rented: "))
starting_odometer = int(input("What was the odometer reading at the start of the rental period (in kilometers): "))
final_odometer = int(input("What was the odometer reading at the end of the the rental period (in kilometers): "))

# Variables
kilometers_driven = int(final_odometer-starting_odometer)
kilometers_driven = int(kilometers_driven)

# CONSTANTS
BASE_RATE_B = 20
KM_RATE_B = 0.3
BASE_RATE_D = 50
KM_RATE_D = 0.3
BASE_RATE_W = 200
WEEK_RATE_W1 = 50
WEEK_RATE_W2 = 100
ADDITIONAL_KM_RATE_W = 0.3

# Calculations
#
# If the user inputs B as their classification code
if classification_code == "B":
    base_charge_B = float(BASE_RATE_B * days_rented)
    perkilometer_charge_B = float(KM_RATE_B * kilometers_driven)
    total_charge = ("%.2f" % (base_charge_B + perkilometer_charge_B))

# If the user inputs D as their classification code

elif classification_code == "D":
    kilometers_per_day = kilometers_driven / days_rented
    base_charge_D = BASE_RATE_D * days_rented
    if kilometers_per_day <= 100:
        perkilometer_charge_D = 0
    else:
        perkilometer_charge_D = KM_RATE_D * (kilometers_per_day - 100)
    total_charge = ("%.2f" % (base_charge_D + perkilometer_charge_D))

# If the user inputs W as their classification code
elif classification_code == "W":
    weeks_number = math.ceil(days_rented/7)
    kilometers_per_week = int(kilometers_driven/weeks_number)
    base_charge_W = float(200*weeks_number)
    if kilometers_per_week <= 1000 :
        week_charge_W = 0
        additional_charge = 0
    elif 1000 < kilometers_per_week <= 2000 :
        week_charge_W = WEEK_RATE_W1*weeks_number
        additional_charge = 0
    else:
        week_charge_W = WEEK_RATE_W2*weeks_number
        km_over = kilometers_per_week-2000
        additional_charge = (ADDITIONAL_KM_RATE_W*km_over)
    total_charge = ("%.2f" % (base_charge_W + week_charge_W + additional_charge))

# Customer Informarion
print()
print("CUSTOMER INFORMATION:")
print()
print("Name: ", customer_name)
print("Classification Code: ", classification_code)
print("Number of Days Rented: ", days_rented)
print("Initial Odometer Reading: ", str(starting_odometer)+" KILOMETERS")
print("Finals Odometer Reading: ", str(final_odometer)+" KILOMETERS")
print("Number of kilometers driven: ", str(kilometers_driven) + " KILOMETERS")
print("Your total bill equals: $", total_charge)
