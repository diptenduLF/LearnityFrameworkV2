/**
 * 
 */

	var index = 0;
	var rowId = 0;
	function findRow(){
		for (var counter=0; counter<document.frm.checkbox.length; counter++) {
			if (document.frm.checkbox[counter].value == role_id) {
				rowId = counter;
			}
		}
	return true;	
	}
	
	function test() {
		var index = 0;
		for (var i=0;i<document.frm.elements.length;i++){
			var e = document.frm.elements[i];
			if (e.type=='radio'){
				index++;
			}
		}
		return index;
	}
	function checkEntries(){
		var filledIn = false;
		var i = test();
		if(i>1) {
			for (var counter=0; counter<document.frm.checkbox.length; counter++)
				if (document.frm.checkbox[counter].checked == true) {
					index = counter;
					filledIn = true;
				}
			if (filledIn == false){
				alert('You must select at least one Template');
				return false;
			}
		}
		if(i==1)  {
			if (document.frm.checkbox.checked == true) {
				filledIn = true;
			}
			if (filledIn == false){
				alert('You must select at least one Template');
				return false;
			}
		}
		return true;
	}

	function addLayout_onclick() {
		if (document.frm.filename.value.length == 0)
			alert ("Select a template zip file first using the Browse button");
		else {
			document.getElementById("status-message").innerHTML = "Starting template zip file upload ..";
			document.frm.method="post";
			document.frm.action = "./coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=0";
			document.frm.encoding = "multipart/form-data";
			document.frm.submit();
		}
	}
	function download_onclick() {
		var i = test();
		if(i==1) {
			if(!checkEntries())
			return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.DownloadDefaultXML?template_id="+document.frm.template_id.value+"&file_name="+document.frm.filename.value;
			document.frm.submit();
		}
		if(i>1) {
			if(!checkEntries())
			return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.DownloadDefaultXML?template_id="+document.frm.template_id.value+"&file_name="+document.frm.filename.value;
			document.frm.submit();
	} 
	} 
	function setDefault_onclick() {
		var i = test();
		if(i==1) {
			if(!checkEntries())
				return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=1&template_id="+document.frm.template_id.value;
			document.frm.submit();
		}
		if(i>1) {
			if(!checkEntries())
				return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=1&template_id="+document.frm.template_id.value;
			document.frm.submit();
	} 
	} 
	function deleteLayout_onclick() {
		var i = test();
		if(i>1) {
			if(!checkEntries())
			return false;
			doyou = confirm("Are you Sure to Delete The Selected Template Item?"); //Your question.

			if (doyou == true) {
//				location.href = "coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=2&template_id="+document.frm.template_id.value;
				checkTemplateDeletion(document.frm.template_id.value);
			}
			else {
			}
		}
		if(i==1) {
			if(!checkEntries())
				return false;
			doyou = confirm("Are you Sure to Delete The Selected Template Item?"); //Your question.

			if (doyou == true) {
//				location.href = "coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=2&template_id="+document.frm.template_id.value;
				checkTemplateDeletion(document.frm.template_id.value);
			}
			else {
			}
		}
		return true;
	}
	function checkbox_onclick() {
		var i = test();
		if(i>1) {
			for(var counter=0; counter<document.frm.checkbox.length; counter++) {
				if(document.frm.checkbox[counter].checked) {
				   document.frm.template_id.value=document.frm.checkbox[counter].value;
                        if(document.frm.defaultvalue[counter].value=='yes'){
									document.frm.defaultvalue1.checked=true;
								}
                        if(document.frm.defaultvalue[counter].value!='yes'){
									document.frm.defaultvalue1.checked=false;
								}
					break;
				}
			}
		}
		if (i==1) {
			if(document.frm.checkbox.checked) {
				   document.frm.template_id.value=document.frm.checkbox.value;
                        if(document.frm.defaultvalue.value=='yes'){
									document.frm.defaultvalue1.checked=true;
								}
                        if(document.frm.defaultvalue[counter].value!='yes'){
									document.frm.defaultvalue1.checked=false;
								}
			}
		}
	}

 function load() {
		var i = test();
		if(i>1) {
 		findRow();
			document.frm.checkbox[rowId].checked=true;
			CCA();
		}
		if(i==1) {
			document.frm.checkbox.checked=true;
			CCA();
		}
	}
/*	function viewLayout_onclick() {
			document.frm.method="post";
			document.frm.target="viewLayout";
			document.frm.action = "manageFileContent?operation=view&resource_id="+document.frm.template_id.value+"&type1=template";
		    window.open("","viewLayout","width=700,height=680,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
			document.frm.submit();
	}
*/
	function showLayout_onclick() {
	var i = test();
		if(i>=1) {
       //  if(document.frm.type.value==template_id){
					document.frm.method="post";
			        document.frm.action = "coreadministrationv2.sysmgmt.ResourceApplicationTemplate?template_id="+document.frm.template_id.value;
		        	window.open("","new11","width=700,height=460,status=yes,scrollbars=no,resizable=no,toolbar=no,menubar=no");
		        	document.frm.target="new11";
			        document.frm.submit();
			}
		
           	else{
			        alert("Please select a Template");
			}
	   	}
	
	function checkTemplateDeletion(template_id){
		
		$.ajax({
	        type: "GET",
	        cache: false,
	        async: true,
	        url: "./coreadministrationv2.sysmgmt.CheckTemplateDeletion",
	        data: "template_id=" + template_id,

	        success: function (data)
	        {
	        	if (data == "OK")
					location.href = "coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=2&template_id="+document.frm.template_id.value;
	        	else {
	        		doyou = confirm("There are interfaces that will have to be refreshed if this template is deleted.Would you still like to delete?");
	    			if (doyou == true) 
	    				location.href = "coreadministrationv2.sysmgmt.ApplicationTemplateManagement?prmAddModify=2&template_id="+document.frm.template_id.value;
	        	}
	        },
	        error: function (xhr) {
	        	alert("An error occured: " + xhr.status + " " + xhr.statusText);                
	        }
	    });
	}