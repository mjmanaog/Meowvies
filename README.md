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
  
### File Structure
Stated here the description of important files and structure
* ```data``` - local db and reusable model
* ```helper``` - all the reusable model
* ```network``` - API handler
* ```ui``` - screens
  * Creating new screen must have these three main components
  1. ```activity``` - serves as the canvas
  2. ```fragment``` - the main user interface (to make it reusable)
  3. ```viewmodel``` - retrieving & processing of data
  * Creating new adapter must have separate these
  1. ```adapter``` - to prepare for populating data and inflating the layout
  2. ```viewholder``` - responsible for applying the ui content of the view

* ```res```
  * ```layout``` 
  1. ```activity_``` for activity layout
  2. ```fragment_``` for fragment layout
  3. ```item_``` for recyclerview's item layout
  4. ```layout_``` for custom layout (toolbar/dialog.. etc..)
  * ```values```
  1. ```dimens``` - used to have standardized ui dimensions (margin, padding, etc)
  2. ```colors``` - added custom colors
  3. ```strings``` - no hardcoded strings in xml
  4. ```style``` - all the ui element reusable custom style declared here
  * ```anim``` - created basic animation for screen transition
  * ```font``` - used custom font: nunitosans
  
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
