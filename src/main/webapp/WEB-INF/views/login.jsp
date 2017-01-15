<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" ng-app="StarterApp">
  <head>
    <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.css">
  	<link href="<c:url value='/static/css/test/appTest.css' />" rel="stylesheet"></link>
  </head>
  <body layout="row" ng-controller="AppCtrl">
    <md-sidenav layout="column" class="md-sidenav-left md-whiteframe-z2" md-component-id="left" md-is-locked-open="$mdMedia('gt-md')">
      <md-toolbar class="md-tall md-hue-2">
        <span flex></span>
        <div layout="column" class="md-toolbar-tools-bottom inset">
          <user-avatar></user-avatar>
          <span></span>
          <div>Firstname Lastname</div>
          <div>email@domainname.com</div>
        </div>
      </md-toolbar>
      <md-list>
      <md-item ng-repeat="item in menu">
        <a>
          <md-item-content md-ink-ripple layout="row" layout-align="start center">
            <div class="inset">
              <ng-md-icon icon="{{item.icon}}"></ng-md-icon>
            </div>
            <div class="inset">{{item.title}}
            </div>
          </md-item-content>
        </a>
      </md-item>
      <md-divider></md-divider>
      <md-subheader>Management</md-subheader>
      <md-item ng-repeat="item in admin">
        <a>
          <md-item-content md-ink-ripple layout="row" layout-align="start center">
            <div class="inset">
              <ng-md-icon icon="{{item.icon}}"></ng-md-icon>
            </div>
            <div class="inset">{{item.title}}
            </div>
          </md-item-content>
        </a>
      </md-item>
    </md-list>
    </md-sidenav>
    <div layout="column" class="relative" layout-fill role="main">
      <md-button class="md-fab md-fab-bottom-right" aria-label="Add" ng-click="showAdd($event)">
        <ng-md-icon icon="add"></ng-md-icon>
      </md-button>
      <md-toolbar ng-show="!showSearch">
        <div class="md-toolbar-tools">
          <md-button ng-click="toggleSidenav('left')" hide-gt-md aria-label="Menu">
            <ng-md-icon icon="menu"></ng-md-icon>
          </md-button>
          <h3>
            Dashboard
          </h3>
          <span flex></span>
          <md-button aria-label="Search" ng-click="showSearch = !showSearch">
            <ng-md-icon icon="search"></ng-md-icon>
          </md-button>
          <md-button aria-label="Open Settings" ng-click="showListBottomSheet($event)">
            <ng-md-icon icon="more_vert"></ng-md-icon>
          </md-button>
        </div>
        <md-tabs md-stretch-tabs class="md-primary" md-selected="data.selectedIndex">
          <md-tab id="tab1" aria-controls="tab1-content">
            Latest
          </md-tab>
          <md-tab id="tab2" aria-controls="tab2-content">
            Favorites
          </md-tab>
        </md-tabs>
      </md-toolbar>
      <md-toolbar class="md-hue-1" ng-show="showSearch">
        <div class="md-toolbar-tools">
          <md-button ng-click="showSearch = !showSearch" aria-label="Back">
            <ng-md-icon icon="arrow_back"></ng-md-icon>
          </md-button>
          <h3 flex="10">
            Back
          </h3>
          <md-input-container md-theme="input" flex>
            <label>&nbsp;</label>
            <input ng-model="search.who" placeholder="enter search">
          </md-input-container>
          <md-button aria-label="Search" ng-click="showSearch = !showSearch">
            <ng-md-icon icon="search"></ng-md-icon>
          </md-button>
          <md-button aria-label="Open Settings" ng-click="showListBottomSheet($event)">
            <ng-md-icon icon="more_vert"></ng-md-icon>
          </md-button>
        </div>
       
      </md-toolbar>
      <md-content flex md-scroll-y>
        <ui-view layout="column" layout-fill layout-padding>
          <div class="inset" hide-sm></div>
            <ng-switch on="data.selectedIndex" class="tabpanel-container">
              <div role="tabpanel"
                   id="tab1-content"
                   aria-labelledby="tab1"
                   ng-switch-when="0"
                   md-swipe-left="next()"
                   md-swipe-right="previous()"
                   layout="row" layout-align="center center">
                  <md-card flex-gt-sm="90" flex-gt-md="80">
                    <md-card-content>
                      <h2>Activity</h2>
                      <md-list>
                        <md-item ng-repeat="item in activity | filter:search">
                          <md-item-content>
                            <div class="md-tile-left inset" hide-sm>
                                <user-avatar></user-avatar>
                            </div>
                            <div class="md-tile-content">
                              <h3>{{item.what}}</h3>
                              <h4>{{item.who}}</h4>
                              <p>
                                {{item.notes}}
                              </p>
                            </div>
                          </md-item-content>
                          <md-divider md-inset hide-sm ng-if="!$last"></md-divider>
                          <md-divider hide-gt-sm ng-if="!$last"></md-divider>
                        </md-item>
                        <md-divider></md-divider>
                        <md-item layout class="inset">
                            <md-button layout layout-align="start center" flex class="md-primary">
                              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z"/></svg> More
                            </md-button>
                        </md-item>
                      </md-list>
                    </md-card-content>
                  </md-card>
              </div>
              <div role="tabpanel"
                   id="tab2-content"
                   aria-labelledby="tab2"
                   ng-switch-when="1"
                   md-swipe-left="next()"
                   md-swipe-right="previous()" 
                   layout="row" layout-align="center center">
                  <md-card flex-gt-sm="90" flex-gt-md="80">
                    <md-card-content>
                      <h2>Favorites</h2>
                      <md-list>
                        <md-item ng-repeat="item in activity | filter:search | orderBy:'-what'">
                          <md-item-content>
                            <div class="md-tile-left inset" hide-sm>
                                <user-avatar></user-avatar>
                            </div>
                            <div class="md-tile-content">
                              <h3>{{item.what}}</h3>
                              <h4>{{item.who}}</h4>
                              <p>
                                {{item.notes}}
                              </p>
                            </div>
                          </md-item-content>
                          <md-divider md-inset hide-sm ng-if="!$last"></md-divider>
                          <md-divider hide-gt-sm ng-if="!$last"></md-divider>
                        </md-item>
                        <md-divider></md-divider>
                        <md-item layout class="inset">
                            <md-button layout layout-align="start center" flex class="md-primary">
                              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z"/></svg> More
                            </md-button>
                        </md-item>
                      </md-list>
                    </md-card-content>
                  </md-card>
              </div>
              
          </ng-switch>
          
        </ui-view>
      </md-content>
    </div>
    <!-- Angular Material Dependencies -->
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular-animate.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular-aria.min.js"></script>

    <script src="//ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.js"></script>
     <script src="<c:url value='/static/js/test/appTest.js' />"></script>
    <script src="//cdn.jsdelivr.net/angular-material-icons/0.4.0/angular-material-icons.min.js"></script> 
  </body>
</html>