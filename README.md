# DPE University Training

[<img width="10%" height="10%" src="https://user-images.githubusercontent.com/120980/174325546-8558160b-7f16-42cb-af0f-511849f22ebc.png">](https://dpeuniversity.gradle.com/)
Checkout all the **free** Maven, Gradle, and DPE courses at the [DPE University][dpe-university]!

## Maven Build Cache Deep Dive - Lab 04: Handling Cache Misses with Normalization

This is a hands-on exercise to go along with the [Maven Build Cache Deep Dive][course-url] training module. In this exercise you will go over the following:

- How to deal with cache misses.

The sample code represents a multi-module build. The module `build-info` generates a file `build-info.properties` containing the current timestamp and the project version. The module `application` uses the artifact produced by `build-info`.

## Prerequisites

- Finished going through the _Troubleshooting Build Cache Misses_ section in Maven Build Cache Deep Dive.
- Java 11+

Steps
-----

1. If you have not completed the previous labs, authenticate Maven with the Develocity server.

> [!NOTE]
> As part of taking this **free** course, you have access to a training instance of Develocity located at:
> ```
>  https://dpeuniversity-develocity.gradle.com/
>  ```
> [Sign in][develocity-url] to this Develocity server using the same account you use for the DPE University.
>
> This server is configured so users can only access the Build Scan® and Build Cache entries they publish.

Run the following command and follow the instructions in your terminal:

 ```shell
 ./mvnw com.gradle:gradle-enterprise-maven-extension:provision-access-key
 ```
> [!NOTE]
> For more ways to authenticate, see the [authentication guide](https://docs.gradle.com/enterprise/maven-extension/#authenticating_with_gradle_enterprise) to see how to provide credentials.
 
2. Notice how the `model` build produces a `build-info.properties` (filtered from `src/main/resources`).

3. In `application/pom.xml` notice that `build-info` is a dependency for the `application` module.

4. Execute the build **multiple times** and identify the reason why the test goal needs to execute every time with the help of the "Build Comparison" tool in Develocity:

    ```shell
    ./mvnw clean install
    ```

5. Configure the Build Scan to [capture the goal inputs](https://docs.gradle.com/enterprise/maven-extension/#how_to_enable).

    Add the following to the `.mvn/gradle-enterprise.xml`:
    ```xml
    <buildScan>
      <capture>
        <goalInputFiles>true</goalInputFiles>
      </capture>
    </buildScan>
    ```

> [!TIP]
> Alternatively, you can enable this setting by adding `-Dgradle.scan.captureGoalInputFiles=true` to your Maven commands. However, it's usually recommended to enable this for all builds unless you have poor network connection.

6. Normalize the runtime classpath to ignore unstable input files.
    See the [ignoring arbitrary files](https://docs.gradle.com/enterprise/maven-extension/#ignoring_arbitrary_files) guide to see how to normalize the `build-info.properties` file from the runtime classpath.

> [!Note]
> The `normalization` element is a _direct_ child of the `gradleEnterprise` element.

7. Execute the build again, at least twice. Verify that there are build cache hits by looking at the Build Scan. Furthermore, check for differences in the build comparison view.

    ```shell
    ./mvnw clean install
    ```

> [!IMPORTANT]
> Instead if ignoring an entire file, you can instead [ignore specific properties](https://docs.gradle.com/enterprise/maven-extension/#ignoring_specific_entries_in_properties_files). See the free [build cache course][course-url] for a detailed walk through.

## Solution Reference

To see the solution to the lab, check out the [`solution`](https://github.com/gradle/cache-misses-maven-build-cache-lab/commit/solution) branch of this project.

## More Free Labs & Courses

Be sure to check out our other **free** [courses][dpe-university] and [labs](https://github.com/gradle?q=dpe-university)!

**Related courses:**
- [Maven - Build Cache Deep Dive][course-url]
- [Maven - Maintaining an Optimized Build Cache](https://dpeuniversity.gradle.com/c/42cf9d626302011526c4a0536b26af929b5bef58)
- [Develocity - How to Use Build Scans](https://dpeuniversity.gradle.com/c/0b0b3e4a8d21709ff39074e9962eee6ca4276dc1)

**Related labs:**
- [Lab 01 - Using the local build cache](https://github.com/gradle/getting-started-maven-build-cache-lab)
- [Lab 02 - Missing Inputs With Build Caching](https://github.com/gradle/missing-inputs-maven-build-cache-lab)
- [Lab 03 - Add Build Cache Support to any Maven Plugin](https://github.com/gradle/caching-any-plugin-maven-build-cache-lab)
- [Lab 04 - Handling Cache Misses with Normalization](https://github.com/gradle/cache-misses-maven-build-cache-lab)
- [Lab 05 - Outputs Overwrite Inputs](https://github.com/gradle/outputs-overwrite-inputs-maven-build-cache-lab)
- [Lab 06 - Maintaining an Optimized Build Cache](https://github.com/gradle/maintaining-optimized-cache-maven-build-cache-lab)

[course-url]: https://dpeuniversity.gradle.com/c/47262fea1e74b719afb590d8cb3f8280bf2af732
[dpe-university]: https://dpeuniversity.gradle.com/
[develocity-url]: https://dpeuniversity-develocity.gradle.com/
