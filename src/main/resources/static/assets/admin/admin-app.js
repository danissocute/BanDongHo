var myApp = angular.module("admin-app", ["ngRoute"]);
myApp.config(function ($routeProvider) {
    // Chuyen Trang
    $routeProvider.when("/product", {
        templateUrl: "/assets/admin/product/index.html",
        controller: "product-ctrl"
    })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/assets/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .otherwise({ redirectTo: "/product" });
});
// Dang ky controller voi app



