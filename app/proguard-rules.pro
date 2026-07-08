# Android specific proguard rules
-keep class android.support.** { *; }
-keep class androidx.** { *; }
-keep interface android.support.** { *; }
-keep interface androidx.** { *; }

# Keep the Application class
-keep class * extends android.app.Application

# Keep R classes
-keep class **.R$* { *; }

# Keep native methods
-keep class * extends java.lang.Object {
    native <methods>;
}

# Keep classes that may be used by reflection
-keep class * implements java.io.Serializable

# Keep all activities
-keep class * extends android.app.Activity

# Keep all fragments
-keep class * extends android.app.Fragment
-keep class * extends androidx.fragment.app.Fragment

# Keep all view models
-keep class * extends androidx.lifecycle.ViewModel

# Keep all LiveData
-keep class * extends androidx.lifecycle.LiveData
