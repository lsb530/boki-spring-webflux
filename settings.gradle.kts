rootProject.name = "boki-spring-webflux"

include("core")
include(
    "spring-mvc:mvc-head-office",
    "spring-mvc:mvc-branch-office"
)
include(
    "spring-reactive:reactive-branch-office"
)
