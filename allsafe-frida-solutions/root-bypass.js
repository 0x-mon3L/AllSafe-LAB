Java.perform(function() {
  // var <class_reference> = Java.use("package_name.classname")
  var cr = Java.use("com.scottyab.rootbeer.RootBeer");
  
  // <class_reference>.<method>.implementation = function(){}
  cr.isRooted.implementation = function() {
    return false;
  }
});