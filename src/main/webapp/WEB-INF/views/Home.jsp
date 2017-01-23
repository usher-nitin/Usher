      <form ng-controller="homeController" name="myForm" class="form-horizontal">
                      <div class="row">
                          <div class="form-group col-md-12">
                              <div class="col-md-7">
                                  <input type="text" ng-model="Question" name="Question" class="username form-control input-sm" placeholder="Enter Question" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.location.$error.required">This is a required field</span>
                                      <span ng-show="myForm.location.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit" value="Submit" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="changeLocation()" class="btn btn-warning btn-sm">Change Location</button>
                          </div>
                      </div>
                      </form>