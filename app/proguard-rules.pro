######################## MVVM With Hilt And Degger Strat #################################################

# Keep public classes and methods in the Retrofit library
-keep class retrofit.* { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep Retrofit interfaces that are used as API endpoints
-keep,allowobfuscation interface com.example.api.YourApiServiceInterface*

# Keep model classes used by Retrofit for JSON parsing
-keepattributes Annotation
-keepattributes Signature
-keep class com.example.model.* { *; }

# Keep ViewModel classes and their methods
-keep class com.example.viewmodel.* { *; }
-keepclasseswithmembers class * {
    @androidx.lifecycle.ViewModelProviders.* <methods>;
}

# Keep Hilt annotations and related classes
-keep class javax.inject.* { *; }
-keep class dagger.hilt.* { *; }
-keep class dagger.hilt.android.* { *; }
-keep class dagger.hilt.lifecycle.* { *; }
-keep class dagger.hilt.internal.* { *; }

# Keep Hilt-generated code
-keepclasseswithmembers class * {
    @dagger.hilt.* <fields>;
}

# Keep ViewModelFactory classes
#-keep class  extends androidx.lifecycle.ViewModelProvider.Factory

# Keep ViewModelInject annotations
-keep @dagger.hilt.android.lifecycle.ViewModelInject.* class *

# Keep ViewModelInject annotated constructors
-keepclassmembers class * {
    @dagger.hilt.android.lifecycle.ViewModelInject* <init>(...);
}

# Keep ViewModel entry points
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

# Keep ViewModel keys
-keepclassmembers class * {
    @javax.inject.* *;
    @dagger.hilt.* * ;
}

# Keep ViewModel entry points
-keepclassmembers class * {
    @dagger.hilt.* <methods>;
}

# Keep Retrofit interfaces and their methods
-keepclasseswithmembers interface * {
    @retrofit2.http.* <methods>;
}

# Keep Dagger modules
-keep class * {
    @dagger.Module.* *;
}

# Keep Dagger-Hilt entry points
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

# Keep Dagger-Hilt components
-keep class * {
    @dagger.hilt.* * ;
}

# Keep Dagger-Hilt component interfaces
-keep class * {
    @dagger.hilt.* * ;
}

# Keep Dagger-Hilt generated code
-keep class * {
    @dagger.hilt.* * ;
}

# Keep Dagger-Hilt annotations
-keep class * {
    @dagger.hilt.* *;
}

# Keep Dagger-Hilt qualified annotations
-keep class * {
    @dagger.hilt.qualifiers.* *;
}

# Keep Dagger-Hilt scopes
-keep class * {
    @dagger.hilt.* *;
}

######################## MVVM With Hilt And Degger End #################################################

######################## All Data Class Decler Here Strat ##############################################

######## Request Class ##########
-keep class com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator*{ *; }
-dontwarn com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator**

######## Response Class ##########
-keep class com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.*{ *; }
-dontwarn com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.**

-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName* <fields>;
}

######################## All Data Class Decler Here End ###################################################

######################## Gson Decler Here Start ###########################################################

-keep class com.google.gson.* { *; }
-keep class org.json.* { *; }
-keep class com.google.gson.reflect.TypeToken* { *; }
-keep class com.google.gson.reflect.TypeToken*
#-keep class  extends com.google.gson.reflect.TypeToken.*
-keep public class * implements java.lang.reflect.Type

######################## Gson Decler Here End #############################################################

######################## Firebase Decler Here Start #######################################################

-keep class com.firebase.* { *; }
-keep class com.google.firebase.* { *; }
-keep class com.google.android.gms.* { *; }
-keep class org.json.* { *; }
-keep public class com.google.android.gms.ads.**{
   public *;
}

######################## Firebase Decler Here End #########################################################

######################## ButterKnife Decler Here Start ####################################################

#ButterKnife
-keep class butterknife.* { *; }
-dontwarn butterknife.internal.**
-keep class *$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

######################## ButterKnife Decler Here End #######################################################

-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

#Attributes
-keepattributes Signature
-keepattributes Annotation
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-keepattributes Exceptions
-keepattributes SourceFile,LineNumberTable

#Specifies not to ignore non-public library classes.
-dontskipnonpubliclibraryclasses

#Specifies not to ignore package visible library class members
-dontskipnonpubliclibraryclassmembers

#Specifies to print any warnings about unresolved references and other important problems, but to continue processing in any case.
-ignorewarnings

-optimizationpasses 5