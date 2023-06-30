const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    $scope.cart = {
        items: [],
        //Them vao gio hang
        add(id) {
            const item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        //Lay tong so luong
        get count() {
            return this.items.length;
            // return this.items.map(item => item.qty)
            //     .reduce((total, qty) => total += qty, 0);
        },
        //Lay tong gia tien
        get amount() {
            return this.items.map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        //Luu gio hang vao local storage
        saveToLocalStorage() {
            const json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        //moi khi load lai thi se la mot request khac items se phi reload
        // nen phai lay du lieu tu localStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createdate: new Date(),
        address: "",
        accounts: {
            //su dung jquery
            username: $('#username').text().trim()
        },
        get orderdetails() {
            return $scope.cart.items.map(item => {
                return {
                    products: {id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            })
        },
        purchase() {
            var order = angular.copy(this);
            // Thực Hiện Đặt Hàng
            throw $http.post("/rest/orders", order).then(resp => {
                alert("Đặt Hàng Thành Công");
                $scope.cart.clear();
                //Đặt xong chuyển về location
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Xảy Ra Lỗi Khi Đặt Hàng")
                console.log(error)
            })
        }
    }
})