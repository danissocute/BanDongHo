myApp.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    $scope.initialize = function () {
        //   load products
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createdate = new Date(item.createdate)
            })
        })
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        })
    }
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }
    $scope.imageChanged = function (files) {
        const data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi Upload Hình Ảnh");
            console.log("erros", error);
        })
    }
    $scope.reset = function () {
        $scope.form = {
            createdate: new Date(),
            image: 'dongho1.webp',
            available: true
        }
    }
    $scope.create = function () {
        let item = angular.copy($scope.form);
        $http.post('/rest/products', item).then(resp => {
            resp.data.createdate = new Date(resp.data.createdate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm Mới Sản Phẩm Thành Công");
        }).catch(error => {
            alert("Lỗi Thêm Mới Sản Phẩm");
            console.log("Error", error);
        });
    }
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập Nhập Sản Phẩm Thành Công");
        }).catch(error => {
            alert("Lỗi Cập Nhập Sản Phẩm");
            console.log(error);
        })
    }
    $scope.delete = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xóa Sản Phẩm Thành Công");
        }).catch(error => {
            alert("Lỗi Xóa Sản Phẩm");
            console.log(error);
        })
    }
    $scope.pager = {
        page: 0,
        size: 3,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }

    }
    $scope.initialize();
});