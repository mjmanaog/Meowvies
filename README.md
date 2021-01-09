# Meowvies
This application only shows the iTunes' movie catalog.

(It's called meowvies bc i love cats and for pun (fun) huhu)

### Features
1. Displays catalog from [iTunes Movie](https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all)
   * Title
   * Genre
   * Price
   * Artwork
   * Long Description
2. Displays all the available genre
3. Sort per genre
3. Displays Recently Viewed Movies
4. Able to handle negative scenario such us internet connectivity issue/ no internet at all.

### MVVM as Architecture
Model-View-ViewModel architectural pattern was used in this project for these reasons:
* **Separation of Concerns** - to separate the user interface from the logic behind the process
* **Readability** - it is easier to understand the code because of the first reason
* **Maintability** - it is easier to maintain and add new features
* **Scalability** - there will be no problem for scaling if in the future there will be added new features in this app
* **Testing/Debugging** - easier to debug because of reason #1. It'll be easier also to add unit testing.

### Persistence
The application used **Room** as persistence library
* The application stores the recently viewed movie and displays it for these reasons:
  * User can easily return to the viewed movie
  * User can view it without the internet connection
  
### Libraries
* Retrofit - REST client
* Lifecycle - Live Data and ViewModel
* Room - Persistence
* RXJava - Observe in API request task (clean and ez)
* Glide - Images from URL handler
* Blurry - Blur image (for aesthetic purposes lol)
  
### Target Version
* MinimumSDK - 21
* TargetSDK - 29


### Meh Side
1. iTunes provides a low-res artwork (artwork100 - 100x100) so gomenasai for the pixelated thumbnails/image.

### APK
![APK](http://api.qrserver.com/v1/create-qr-code/?color=000000&bgcolor=FFFFFF&data=https%3A%2F%2Fdrive.google.com%2Fdrive%2Ffolders%2F10li16LNbdkpxZtcmrvAkOy9g1tDg8mr_%3Fusp%3Dsharing&qzone=1&margin=0&size=150x150&ecc=L)
