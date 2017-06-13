/*
 * @author : Kopal Darbari
 * 
 * This component is just used for showing navigation bar and its items which it reads from a json array object
 * It should have following items :
 *  navItem - name of the item which will be displayed
 *  glyph   - glyphicon class for the nav item
 *  state   - state name for redirection
 *  
 */
var app = angular.module('commonComponents');

app.component('navbarWidget', {

    templateUrl: 'components/navbar-widget/template/navbar-widget.html',

    controller: function ($scope) {
    	    	
    	$(function () {
    	    $('.navbar-toggler').on('click', function(event) {
    			event.preventDefault();
    			$(this).closest('.navbar-minimal').toggleClass('open');
    		})
    	});

    	$scope.navItems = [
	               {
	                   id: 1,
	                   navItem: 'Users',
	                   glyph: 'glyphicon-user',
	                   state: 'mains.users.list'
	               },
	               {
	                   id: 2,
	                   navItem: 'Transactions',
	                   glyph: 'glyphicon-list-alt',
	                   state: 'mains.transactions.list'
	               },
	               {
	                   id: 3,
	                   navItem: 'Fee Commission',
	                   glyph: 'glyphicon glyphicon-usd',
	                   state: 'mains.feeAndCommission.list'
	               },
	               {
	                   id: 4,
	                   navItem: 'Logout',
	                   glyph: 'glyphicon-log-out',
	                   state: 'accounts.logout'
	               }
	           ];
    	
    }
    });