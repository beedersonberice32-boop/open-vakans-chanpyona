# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Preserve line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Preserve all public classes and methods
-keep public class * {
    public protected *;
}

# Preserve Room
-keep class * extends androidx.room.RoomDatabase
-keep @interface androidx.room.*
-keepclasseswithmembernames class * {
    @androidx.room.* <fields>;
    @androidx.room.* <methods>;
}

# Preserve Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Preserve Serialization
-keepclassmembers class ** {
    @com.google.gson.annotations.SerializedName <fields>;
}
