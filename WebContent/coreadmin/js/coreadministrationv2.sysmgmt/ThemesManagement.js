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
				alert('You must select at least one Themes');
				return false;
			}
		}
		if(i==1)  {
			if (document.frm.checkbox.checked == true) {
				filledIn = true;
			}
			if (filledIn == false){
				alert('You must select at least one Themes');
				return false;
			}
		}
		return true;
	}

	function addLayout_onclick() {
		document.frm.method="post";
		document.frm.action = "./coreadministrationv2.sysmgmt.ThemesManagement?prmAddModify=0";
		document.frm.encoding = "multipart/form-data";
		document.frm.submit();
	}
	function download_onclick() {
		var i = test();
		if(i==1) {
			if(!checkEntries())
			return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.DownloadThemes?themes_id="+document.frm.themes_id.value;
			document.frm.submit();
		}
		if(i>1) {
			if(!checkEntries())
			return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.DownloadThemes?themes_id="+document.frm.themes_id.value;
			document.frm.submit();
	} 
	} 
	function setDefault_onclick() {
		var i = test();
		if(i==1) {
			if(!checkEntries())
				return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.ThemesManagement?prmAddModify=1&themes_id="+document.frm.themes_id.value;
			document.frm.submit();
		}
		if(i>1) {
			if(!checkEntries())
				return false;
			document.frm.method="post";
			document.frm.action = "coreadministrationv2.sysmgmt.ThemesManagement?prmAddModify=1&themes_id="+document.frm.themes_id.value;
			document.frm.submit();
	} 
	} 
	function deleteLayout_onclick() {
		var i = test();
		if(i>1) {
			if(!checkEntries())
			return false;
			doyou = confirm("Are you Sure to Delete The Selected Themes Item?"); //Your question.

			if (doyou == true) {
				location.href = "coreadministrationv2.sysmgmt.ThemesManagement?prmAddModify=2&themes_id="+document.frm.themes_id.value;
			}
			else {
			}
		}
		if(i==1) {
			if(!checkEntries())
				return false;
			doyou = confirm("Are you Sure to Delete The Selected Themes Item?"); //Your question.

			if (doyou == true) {
				location.href = "coreadministrationv2.sysmgmt.ThemesManagement?prmAddModify=2&themes_id="+document.frm.themes_id.value;
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
				   document.frm.themes_id.value=document.frm.themesid[counter].value;
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
				   document.frm.themes_id.value=document.frm.themesid.value;
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
	function viewLayout_onclick() {
			document.frm.method="post";
			document.frm.target="viewLayout";
			document.frm.action = "coreadministrationv2.sysmgmt.ManageFileContent?operation=view&resource_id="+document.frm.themes_id.value+"&type1=theme";
		    window.open("","viewLayout","width=700,height=680,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
			document.frm.submit();
	}