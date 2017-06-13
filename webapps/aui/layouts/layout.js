angular
	.module('layoutModule', ['ngAnimate'])
    .controller('sidebarController', sidebarController);

sidebarController.$inject = ['$state','$translate','$timeout'];

function sidebarController($state, $translate,$timeout){

	var sidebar = this;
	
	
	
	
	sidebar.items = 'li > a.nav-item';
	sidebar.submenu = 'ul.submenu';
	sidebar.menuState = false;
    

	//methods
	
	
	sidebar.toggleMenu = toggleMenu;
	sidebar.toggleContent=toggleContent;
	//init 
	
	
	function toggleMenu()
	{  
		sidebar.menuState = !sidebar.menuState;
	}
	
	function toggleContent(){
		
		sidebar.childState= !sidebar.childState;
	}
	
	sidebar.navigations = [
	               {
	                   id: 1,
	                   navItem: 'User',
	                  children:[
	                	  {
	                		  id:1,
	                		  submenu: 'Manage User'
	                		  
	                	  },
	                	  {
	                		  id:2,
	                		  submenu: 'User Transaction'
	                		  
	                	  }
	                	  
	                	  
	                  ]
		                   
	                   
	               
	               },
	               {
	                   id: 2,
	                   navItem: 'ikhjh',
	                   children:[
		                	  {
		                		  id:1,
		                		  submenu: 'Manage User'
		                		  
		                	  },
		                	  {
		                		  id:2,
		                		  submenu: 'User Transaction'
		                		  
		                	  }
		                	  
		                	  
		                  ]
			                
	                   
	               
	               },
	               {
	                   id: 3,
	                   navItem: 'UkhdskjhfSER',
	                   children:[
		                	  {
		                		  id:1,
		                		  submenu: 'Manage User'
		                		  
		                	  },
		                	  {
		                		  id:2,
		                		  submenu: 'User Transaction'
		                		  
		                	  }
		                	  
		                	  
		                  ]
			                
	                   
	               
	               },
	               {
	                   id: 4,
	                   navItem: 'ehjhjhfjk',
	                   children:[
		                	  {
		                		  id:1,
		                		  submenu: 'Manage User'
		                		  
		                	  },
		                	  {
		                		  id:2,
		                		  submenu: 'User Transaction'
		                		  
		                	  }
		                	  
		                	  
		                  ]
			                
	                   
	               
	               }
	              
	               
	           ]; 
	
}