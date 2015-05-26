/**
 * Created by ST on 14/05/2014.
 */

angular.module("rdvmedecins").directive('selectEnable2', ['$timeout', 'utils', function ($timeout, utils) {
  return {
    link: function (scope, element, attrs) {
      utils.debug("directive selectEnable2 attrs", attrs);
      $timeout(function () {
        $('#' + attrs['id']).selectpicker();
      })
    }
  }
}]);