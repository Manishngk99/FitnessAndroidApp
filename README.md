# stw300cem-final-assignment-Manishngk99
stw300cem-final-assignment-Manishngk99 created by GitHub Classroom

Title- Fitness Club

Introduction
The Fitness Club is fitness application, which collect user data based on user health status,
provide products reguarding health related and provided exercise needed for health care.
The Fitness Club aims to provide a wellness application for android platform capable to store
and track users’ health status, giving a steps and methods to
improve health lifestyle choices.

Features of your project
1. Login and register of user
2. User additional Bio information can be inserted.
3. User data update.
4. User health status and display catagory of fitness status.
5. Exercise is displayed with timer.
6. Product display throught database.
7. Category of product is displayed.


Android project Youtube video link
https://youtu.be/eB42MT9BlqU

API link
https://github.com/softwarica-github/t2-backend-api-Manishngk99.git

Retrofit
Retrofit turns the HTTP API into java interface.
It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.

To use it, there are three classes needed,
1. Model class which is used as a JSON model
2. Interfaces that define the possible HTTP operations
3. Retrofit.Builder class - Instance which uses the interface
and the Builder API to allow defining the URL end point for the HTTP operations.

Every method of an interface represents one possible API call. It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL. The return value wraps the response in a Call object with the type of the expected result.

@GET("getUser")
    Call<UserDataModel> displayUser(@Header("Authorization") String token);
  
You can use replacement blocks and query parameters to adjust the URL. A replacement block is added to the relative URL with {}. With the help of the @Path annotation on the method parameter, the value of that parameter is bound to the specific replacement block.

  @PUT("addToCart/{id}")
    Call<HomeContentModel> addCart(@Path("id") int id, @Header("Authorization") String token);
  
The @Body annotation on a method parameter tells Retrofit to use the object as the request body for the call.

   @POST("/registration")
    Call<SignupResponse> registerUser(@Body UserModel userModel);
