import math

##
#My name is Tyler Roberts and I created a program which computes the areas of cubes, pyramids and ellipsoids.
#Once the volumes are calculated, they are then stored in their lists and displayed once the program exits.
#

#Function for calculating volume of a cube
def volume_cube(length):
    volume = length**3
    volume = round(volume,1)
    return volume

#Function for calculating volume of a pyramid
def volume_pyramid(base,height):
    volume = float((1/3)*(base**2)*height)
    volume = round(volume,1)
    return volume

#Function for calculating volume of an ellipsoid
def volume_ellipsoid(radius1,radius2,radius3):
    volume = (4/3)*math.pi*(radius1)*(radius2)*(radius3)
    volume = round(volume,1)
    return volume

which_volume = (input("Would you like to calculate the volume of a cube, a pyramid, an ellipsoid or would you like to quit: ")).upper()

#The lists where the calculated volumes will be stored
CubeVolumes = []
PyramidVolumes = []
EllipsoidVolumes = []

#If the user does not input one of the four valid input options
while which_volume not in ("CUBE", "PYRAMID", "ELLIPSOID", "QUIT"):
    which_volume = (input("Would you like to calculate the volume of a cube, a pyramid, an ellipsoid or would you like to quit: ")).upper()

#If the user inputs one of the four valid input options
while which_volume in ("CUBE", "PYRAMID", "ELLIPSOID", "QUIT"):

    # If the user inputs cube then they will input the length of the cube
    #The volume of the cube will then be calculated, printed to the screen and stored in it's list
    if which_volume == "CUBE":
        length = float(input("What would you like the length to be: "))
        print("The volume of a cube with length",length,"is", volume_cube(length))
        CubeVolumes.append(volume_cube(length))

    # If the user inputs pyramid then they will input the base and height of the pyramid
    #The volume of the pyramid will be calculated, printed to the screen and stored in it's list
    elif which_volume == "PYRAMID":
        base = float(input("Enter the length of the pyramid's base: "))
        height = float(input("Enter the height of the pyramid: "))
        print("The volume of a pyramid with base",base, "and height", height,"is", volume_pyramid(base,height))
        PyramidVolumes.append(volume_pyramid(base, height))

    #If the user inputs ellipsoid then they will input the three radii of the ellipsoid
    #The volume of the ellipsoid will be calculated, printed to the screen and stored in it's list
    elif which_volume == "ELLIPSOID":
        radius1 = float(input("Enter the first radius of the ellipsoid: "))
        radius2 = float(input("Enter the second radius of the ellipsoid: "))
        radius3 = float(input("Enter the third radius of the ellipsoid: "))
        print("The volume of an ellipsoid with radius",radius1,",radius",radius2,"and radius",radius3,"is", volume_ellipsoid(radius1,radius2,radius3))
        EllipsoidVolumes.append(volume_ellipsoid(radius1,radius2,radius3))

    #If the user inputs quit before calculating volumes then a message will be printed and the program will end
    #If the user inputs quit after calculation volumes then a message and the stored volumes will be printed and the program will end
    elif which_volume == "QUIT":
        if len(CubeVolumes) == 0 and len(PyramidVolumes) == 0 and len(EllipsoidVolumes) == 0:
            print("You have come the end of the session.")
            print("You did not perform any volume calculations.")
            exit()
        else:
            print("You have come to the end of the session.")
            print("The volumes calculated are shown below.")
            print("Cube: ", sorted(CubeVolumes))
            print("Pyramid: ", sorted(PyramidVolumes))
            print("Ellipsoid: ", sorted(EllipsoidVolumes))
            exit()

    which_volume = (input("Would you like to calculate the volume of a cube, a pyramid, an ellipsoid or would you like to quit:")).upper()
    while which_volume not in ("CUBE", "PYRAMID", "ELLIPSOID", "QUIT"):
        which_volume = (input("Would you like to calculate the volume of a cube, a pyramid, an ellipsoid or would you like to quit: ")).upper()
