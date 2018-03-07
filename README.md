# camera
This program will calculate minimum amount of pictures needed to capture objects through a window.

Gogol is rolling out its new camera on wheels for its program called Gogol Road View. It can take pictures
at a surprising 5e5 megapixel. However, each photo takes a lot of energy so Gogol needs help determining
the smallest number of pictures to take such that each object can be in view of at least one of the taken
photos. Each time a picture is taken every object with a direct line of sight of the Gogol Camera Vehicle
(GCV) is captured by the GCV and is thus in the corresponding photo.

For the current situation the GCV will be moving in a straight line parallel to an infinitely long wall called
the Great Occluder. Unfortunately for Gogol, some objects that Gogol wants to take a picture of is on the
opposite side of the wall from GCV’s path. There does exist a single stretch in the wall (the Glass Partition)
that allows for photons to pass, such that photos can be taken of objects on the opposite side. In general
if the only part of the wall directly between the object and the camera is the Glass Partition, then a photo
taken at that point will contain the object.

Input:
The first line of input begins with two positive integers, n and d (n < 100,000; d < 100,000), which
represents the number of objects and the distance (in meters) in the positive y direction the Great
Occluder is from the path of the GCV. The next line contains two positive integers, b and e (b < e <
100,000), which represent the beginning and ending index of the Glass Partition in meters from the y-axis.
The remaining n lines contain two positive integers, xi and yi (xi, yi < 100,000), where the i-th pair (xi, yi)
represent the Cartesian coordinate of the i-th object in meters from the origin.

Output:
Output a single integer the minimum number of pictures needed to be taken to photograph each object
at least once.

To compile:
javac camera.java

To run:
java camera
