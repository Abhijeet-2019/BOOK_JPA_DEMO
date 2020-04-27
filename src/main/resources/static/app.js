var app = angular.module("myApp", ['ngRoute','ui.grid']);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "index.html"
    });

});
app.controller('myCtrl',
   function($scope,$http) {
    alert("Welcome Abhijeet");
    $scope.displayText = false;

    $scope.bookDetails = {
                bookName:"",
                authorName : "",
                publisherName : "",
                location : "",
                libraryName : "",
                telephone :""
        };

    $scope.searchType = '';
    $scope.searchCriteria = '';
    $scope.searchTypes = ["All", "Book Name", "Author Name", "Library Name"];
    $scope.url ="";
    $scope.displaySearchButton = true;
    $scope.displayUpdateButton = false;

    $scope.displaySearch = function(){
        $scope.displaySearchButton = true;
        $scope.displayUpdateButton = false;
    }

    $scope.displayAddUpdate = function(){
            $scope.displaySearchButton = false;
            $scope.displayUpdateButton = true;
        }
    $scope.addBooks = function(){
        alert(""+$scope.bookDetails.bookName);
        $scope.url = 'http://localhost:8080/librariesDetails/savebookDetails';
           $http({
                   method :"POST",
                      url:  $scope.url,
                      data : $scope.bookDetails
                   }).then(function successCallback(response) {
                                alert("data saved");
                                 $scope.searchCriteria  ="";
                                 }, function errorCallback(response)
                                 {
                                   alert("UnsuccessFull call!");
                                 });
    }
    $scope.submit = function(){
     if("All" == $scope.searchType){
           $scope.url = 'http://localhost:8080/librariesDetails/fetchAllBookDetails';
       }
       if("Book Name" == $scope.searchType || "Author Name" == $scope.searchType ||  "Library Name" == $scope.searchType) {
            $scope.url = 'http://localhost:8080/librariesDetails/fetchBookByName?searchCriteria='+$scope.searchCriteria+"&searchType="+$scope.searchType;
       }
       $scope.submitRequest();
       }
        $scope.submitRequest = function(){
        $http({
            method: 'GET',
              url:  $scope.url
            }).then(function successCallback(response) {
            $scope.searchBookGridOptions.data= $scope.prepareGridData(response.data.responseData);
            $scope.searchCriteria  ="";
            }, function errorCallback(response)
            {
              alert("UnsuccessFull call!");
            });
    }

    $scope.refreshPage = function(){

        if("All" == $scope.searchType){
        $scope.displayText = false;
        }else {
        $scope.displayText = true;
        }
    }

    $scope.prepareGridData = function(resultData){
        var dataInGrid = [];
        angular.forEach(resultData , function(record){
            dataInGrid.push(record);
        });
        return dataInGrid;
    }

    var columnArray = [
    {name : 'bookName', displayName : 'Book Name', visibility : true},
    {name : 'authorName', displayName : 'Author Name', visibility : true},
    {name : 'library.libraryName', displayName : 'Library Name', visibility : true},
    {name : 'publisherName', displayName : 'Publisher Name', visibility : true},
    {name : 'availability', displayName : 'Availability', visibility : true},
    {name : 'library.libraryLocation', displayName : 'Location', visibility : true},
    {name : 'library.telephone', displayName : 'Telephone', visibility : true}
    ];

    $scope.searchBookGridOptions = {
        columnDefs: columnArray,
        rowHeight :40
    }
});