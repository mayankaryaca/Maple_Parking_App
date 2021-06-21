# Maple_Parking_App

## :books: Project Description

Maple Parking Application allows user to save parking location and get access to it later when they are trying to find the location.

A user can login/Sign up. Backend is using FirebaseAuthentication to register and login/Signup users. The home screen displays the list of previous parkings

User can add new parking via add parking. The user can enter address and geo location is used to save coordinates or the user can use automatic geolocation picker to recieve his current location also can enter manually.

The profile displays profile information. Also, provide Edit profile details functionality.

The user can Delete profile by providing password authentication before deleting the account permanently.

Android Emulator: Google Pixel 4XL

## Authors

[Wanja Mascarenhas](https://github.com/mascarenhaswanja)
student ID: 101280022
    
[Mayank](https://github.com/mayankaryaca)
student ID: 101300566
    
## :gear: Funcionalities

### :woman_office_worker: :man_office_worker: User Profile

    - Sign-up
    - Sign-in
    - Sign-out
    - Update profile
    - Delete account 
    
#### Entity User
    - Name  
    - Email 
    - Password 
    - Contact number 
    - Car plate number 
       
### :car: Add Parking

    - User have two possibilities to enter create a Parking
        Street name 
        Using current location of the device  
        
    • The user can have a single car plate in their profile but they must be able to add parking with a different car plate.
    • Multiple user can use same car plate number when adding a parking.

#### Entity Parking
    
    - Building code (exactly 5 alphanumeric characters)
    - No. of hours intended to park (1-hour or less, 4-hour, 12-hour, 24-hour)
    - Car License Plate Number (min 2, max 8 alphanumeric characters)
    - Suit no. of host (min 2, max 5 alphanumeric characters)
    - Parking location (street address, lat and lng)
    - Date and time of parking (use system date/time)

### :parking: View Parking

    - View the list of all the parking the user made.
    - Display the list with most recent parking first.
    - Display the detail view of each parking when user taps on any item of the list.
    - When displaying detail view, display all the information about the parking
    - In the detail view of parking, allow the user to open the parking location on map.

### :parking: Edit Parking
    
        - Allow user to edit the parking information except datetime of parking. 
        - Allow user to delete any parking from the list.

## :woman_student: :man_student: Students tasks

``` sh

    For this project we choose Cloud Firestore Database solution 

    - By Mayank
        - Sign In
 
    - By Wanja:     
        - Sign out
 
```
---

### 🖼️ Screenshots

![Screenshot_1622850875](https://user-images.githubusercontent.com/78885735/120873097-bb09fb00-c56e-11eb-9fc0-c153e1cb3872.png)

#### Sign In

![](./screenshots/SignIn.png)

![Screenshot_1622850793](https://user-images.githubusercontent.com/78885735/120873072-a168b380-c56e-11eb-8f9e-0587a36042c6.png)

---

#### Sign Up

![](./screenshots/SignUp.png)

---

#### Add Parking 

![](./screenshots/AddParking.png) 
![Screenshot_1624292369](https://user-images.githubusercontent.com/78885735/122795235-1c2e1000-d28b-11eb-8a8c-d18b054b0503.png)


--- 

#### History Parking 

![](./screenshots/HistoryParking.png) 
![Screenshot_1624292372](https://user-images.githubusercontent.com/78885735/122795255-2223f100-d28b-11eb-80cd-4690e736fb88.png)


--- 

#### Detail Parking 

![](./screenshots/DetailParking.png) 
![Screenshot_1624292390](https://user-images.githubusercontent.com/78885735/122795279-27813b80-d28b-11eb-8f48-38644524ea7c.png)


--- 


#### Detail Profile

![Screenshot_1624292296](https://user-images.githubusercontent.com/78885735/122795364-3ec02900-d28b-11eb-8129-25aee42959ac.png)


#### Edit Profile


![Screenshot_1624292376](https://user-images.githubusercontent.com/78885735/122795357-3bc53880-d28b-11eb-93cc-73e4509daf0f.png)


Task Division List for Maple App

[Maple_Parking_App.csv](https://github.com/mayankaryaca/Maple_Parking_App/files/6593555/Maple_Parking_App.csv)

-----


