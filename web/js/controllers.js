controllers.controller('MessengerController', function($scope, Messages) {
    $scope.username = 'anonymous';

    $scope.Messages = Messages;

    $scope.submit = function (new_message) {
        if (!new_message) {
            return;
        }
        Messages.send({
            username: $scope.username,
            message: new_message
        });
        $scope.new_message = '';
    };
});