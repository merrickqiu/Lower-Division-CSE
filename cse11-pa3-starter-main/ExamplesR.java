class R {
    String s;
    R reference;

    R (String s, R reference) {
        this.s = s;
        this.reference = reference;
    }
}

class ExamplesR {
    R example = new R("blah", null);
    R example2 = new R("blah2", example);
    // Can be constructed, but the first R must have a null value for R.
    // (R is an implementation of a linked list that stores strings).
}

// Tester Library v.3.0
// -----------------------------------
// Tests defined in the class: ExamplesR:
// ---------------------------
// ExamplesR:
// ---------------
// new ExamplesR:1(
//  this.example = new R:2(
//   this.s = "blah"
//   this.reference = null)
//  this.example2 = new R:3(
//   this.s = "blah2"
//   this.reference = R:2))
// ---------------
// No test methods found.