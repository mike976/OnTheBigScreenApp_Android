# 
# 🎬 On The Big Screen

Discover new releases and search through The Movie Database (TMDb) for old and recent movies. This app provides details and descriptions for your favourites movies and tv shows, along with images and links to film trailers.

Supports Android 8.0 and above

Features coming soon include:
- Actor Bio
- Search by Genre and Actor
- Explore related movies and tv shows by Actor

v1.2 is now available for download on your Android devices 

## 🔎 About

On the Big Screen is built using Kotlin 1.3.72 and Android Studio 4

## Download

<a href="https://play.google.com/store/apps/details?id=com.mike976.onthebigscreen">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

<br />

<p>
   
   <img src="https://user-images.githubusercontent.com/63581689/85455089-7bccdf00-b595-11ea-97f7-54e10cb803ca.png" width="210">
   
   <img src="https://user-images.githubusercontent.com/63581689/85455128-84251a00-b595-11ea-8982-42947b130607.png" width="210">
   
   <img src="https://user-images.githubusercontent.com/63581689/85455181-91420900-b595-11ea-9588-dfddb4d29823.png" width="210">
   
   <img src="https://user-images.githubusercontent.com/63581689/85455191-92733600-b595-11ea-9a6d-de7ce4e56f25.png" width="210">
</p>

## Dependencies
- Dagger 2
- Retrofit
- Jetpack Paging Library
- LiveData
- YouTubePlayerView 3rd party control
- StrRatingsControl
- Glide
- Mockito


## 🔧 Usage

In order to use the app, you can use the current TMDb API Key provided in this repo or request one by signing up there:
https://www.themoviedb.org


## 📫 Author

Michael Bullock - <mikebullock976@gmail.com>


## App Design

v1.2 - from development time to release = 2 weeks

- Interface based design
- Dependency constructor & field injection via Dagger 2
- Implementation follows the MVVM design pattern
- Use of Restful API with back-end services #themoviedb
- Single Activity app 
- UI made up of Fragments
- Use of RecyclerViews - Featured Page is combination of horizontal and vertical linear layouts reflecting a App Store style UI page
- ConstraintLayouts - UI views are constrained by there counterparts
- Infinite scrolling and pagination implemented for the Featured Category pages
- Unit Tests implemented with mocks via Mockito



