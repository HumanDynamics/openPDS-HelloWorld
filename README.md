openPDS-HelloWorld
==================

A simple skeleton Android app for an openPDS / RegistryServer install

To get started, make sure you have the Android SDK and Eclipse installed your computer (Android Studio should work, but has not been tested), then pull down the code, along with [Funf] (https://github.com/OpenSensing/funf-v4) and the [openPDS Client] (https://github.com/HumanDynamics/openPDSClient). Reference both Funf and the openPDS Client library from this project (either as a project reference or as a compiled .jar file). At this point, the skeleton application should compile but will likely not function properly as there are dummy-values hard-coded in the application to be filled in. 

As this app is intended as a starting point for writing Android apps on top of openPDS, configuration details are hard-coded in [MainActivity.java] (https://github.com/HumanDynamics/openPDS-HelloWorld/blob/master/HelloPDS/src/edu/mit/media/hellopds/MainActivity.java). From there, you can set the URL to your registry server, as well as the client credentials for the client you've created on that registry server, and the user credentials for your account on that registry server.

The code in [MainActivity.java] (https://github.com/HumanDynamics/openPDS-HelloWorld/blob/master/HelloPDS/src/edu/mit/media/hellopds/MainActivity.java) is commented to explain which values to fill in where, and is set to log in and authorize a pre-existing user on the registry server you've chosen. There is also code - commented out - to create a new user on the registry with provided hard-coded credentials. 

At this point, your application has successfully been authorized to interface with your PDS. The [openPDS client library] (https://github.com/HumanDynamics/openPDSClient) can now be used within your application to interface with the PDS.
