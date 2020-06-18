# book-player
android book player 


How to publish the jar/aar
```gradle
apply plugin: 'maven-publish'


task androidSourcesJar(type: Jar) {
    classifier = 'sources'
    from android.sourceSets.main.java.sourceFiles
}

publishing {
    publications {
        maven(MavenPublication) {
            groupId 'com.arbordale.highlightebookplayer'
            artifactId 'highlightebookplayer'
            version '1.51'
            artifact("$buildDir/outputs/aar/highlightebookplayer-debug.aar")
            pom.withXml {
                // for dependencies and exclusions
                def dependenciesNode = asNode().appendNode('dependencies')
                configurations.implementation.allDependencies.withType(ModuleDependency) {
                    ModuleDependency dp ->
                        def dependencyNode = dependenciesNode.appendNode('dependency')
                        dependencyNode.appendNode('groupId', dp.group)
                        dependencyNode.appendNode('artifactId', dp.name)
                        dependencyNode.appendNode('version', dp.version)
                        // for exclusions
                        if (dp.excludeRules.size() > 0) {
                            def exclusions = dependencyNode.appendNode('exclusions')
                            dp.excludeRules.each {
                                ExcludeRule ex ->
                                    def exclusion = exclusions.appendNode('exclusion')
                                    exclusion.appendNode('groupId', ex.group)
                                    exclusion.appendNode('artifactId', ex.module)
                            }
                        }
                }
            }
        }
    }

    repositories {
        maven {
            url "/Users/sainik/.m2/repository"
        }
    }
}

configurations {
    archives {
        extendsFrom configurations.default
    }
}
```


How to build+run test and publish to local .m2 

```
../gradlew clean assemble publishToMavenLocal
```

https://preview.themeforest.net/item/wrapkit-bootstrap-4-multipurpose-template/full_screen_preview/20957775?clickthrough_id=1394155239&redirect_back=true&ref=cirvitis


How to build the projects for book view

../gradlew clean
../gradlew build
../gradlew publishToMavenLocal