# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class getName to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file getName.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontpreverify
-verbose

-keep, allowobfuscation class com.sixfingers.bp.configparser.*
-keepclassmembers, allowobfuscation class * {
    *;
}

-keepnames class com.sixfingers.bp.configparser.ConfigFileJSONParser
-keepclassmembernames class com.sixfingers.bp.configparser.ConfigFileJSONParser {
    public <methods>;
    public <fields>;
}