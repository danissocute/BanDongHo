myApp.controller("authority-ctrl", function ($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];
    $scope.initialize = [];
    $scope.initialize = function () {
        //    load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })
        //    load staffs and directors(administrators)
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })
    }
    $scope.initialize();
    //Với acc và role truyền vào tìm xem có tồn tại hây ko nếu có trả về true
    $scope.authority_of = function (acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.accounts.username == acc.username && ur.roles.id == role.id);
        }
    }
    //Thêm mới authority
    $scope.grant_authority = function (authority) {
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data);
            alert("Cấp quyền thành công");
        }).catch(error => {
            alert("Cấp quyền thất bại");
            console.log("Error", error);
        })
    }
    //Xóa bỏ authority
    $scope.revoke_authority = function (authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert("Thu hồi quyền thành công");
        }).catch(error => {
            alert("Thu hồi quyền thất bại");
            console.log("Error", error);
        })
    }
    $scope.authority_change = function (acc, role) {
        let authority = $scope.authority_of(acc, role);
        if (authority) {  //nếu có quyên => thu hồi
            $scope.revoke_authority(authority);
        } else { //không có quyên => cấp quyền
            authority = {
                accounts: acc,
                roles: role
            };
            $scope.grant_authority(authority);
        }
    }
});