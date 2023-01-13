
function getValue(variable)
{ 
var v1 = dwr.util.getValue(variable);
 return v1;
}

function getText(variable)
{ 
var v1 = dwr.util.getText(variable);
 return v1;
}

function setValue(variable,data)
{
dwr.util.setValue(variable, data, { escapeHtml:false});
}

function parsePartialHtml(html) {
	  var doc = new DOMParser().parseFromString(html, 'text/html'),
	      frag = document.createDocumentFragment(),
	      childNodes = doc.body.childNodes;

	  while (childNodes.length) frag.appendChild(childNodes[0]);

	  return frag;
}
function fixScriptsSoTheyAreExecuted(el) {
	  var scripts = el.querySelectorAll('script'),
	      script, fixedScript, i, len;

	  for (i = 0, len = scripts.length; i < len; i++) {
	    script = scripts[i];

	    fixedScript = document.createElement('script');
	    fixedScript.type = script.type;
	    if (script.innerHTML) fixedScript.innerHTML = script.innerHTML;
	    else fixedScript.src = script.src;
	    fixedScript.async = false;

	    script.parentNode.replaceChild(fixedScript, script);
	  }
}

function setFragment(part_id,data)
{
/*	
	var e=data;
	//alert(data);
	var start=e.indexOf("<BODY>");
	var end=e.indexOf("</BODY>");
	var subcontent=e.substring(start+6,end);
	//alert(subcontent);
	var x=document.getElementById(part_id);
	$(x).html(subcontent);

	var s = x.getElementsByTagName('script');
	for (var i = 0; i < s.length ; i++) {
	  var node=s[i], parent=node.parentElement, d = document.createElement('script');
	  d.async=node.async;
	  d.src=node.src;
	  d.textContent=node.textContent;
	  d.setAttribute('type','text/javascript');
	  parent.insertBefore(d,node);
	  parent.removeChild(node);
	}
*/	

	var e=data;
	var start=e.indexOf("<BODY>");
	var end=e.indexOf("</BODY>");
	var subcontent=e.substring(start+6,end);
	var domFragment = $.parseHTML($.trim(subcontent),document,true);
	
//	var domFragment = parsePartialHtml(subcontent);
//	fixScriptsSoTheyAreExecuted(frag);	

	var x=document.getElementById(part_id);
	$(x).empty();
	$(x).append(domFragment);	

}

function setReloadGrid(variable)
{
	$("#"+variable).trigger('reloadGrid');			
}

function setReloadGridFirstPage(variable)
{
	$("#"+variable).trigger('reloadGrid', [{page:1}]);			
}

function  setDropDown(partid,value)
{
	var x=document.getElementById(partid);
	$(x).html("<option value='0'>Choose One</option>"+value);
}

function  setDropDownwithAll(partid,value)
{
	var x=document.getElementById(partid);
	$(x).html("<option value='0'>All</option>"+value);
}

function  setDropDownData(partid,value)
{
	var x=document.getElementById(partid);
	$(x).html(value);
}

function getSelectedRow(partid,row_id)
{
	var sr=jQuery("#"+partid).getGridParam('selrow');
	rowdat=jQuery("#"+partid).getRowData(sr);
	var v1=eval('rowdat.'+row_id);
	return v1;
}

function getIdOfSelectedRow(partid)
{
	var sr=jQuery("#"+partid).getGridParam('selrow');
	return sr;
}

/*function getCellDataOfSelectedRow(partid,col_name)
{
	var sr=jQuery("#"+partid).getGridParam('selrow');
	var cell_data = jQuery("#"+partid).getCell(sr,col_name);
	return sr;
}*/

function getNoOfRows(partid)
{
	var noOfRows=jQuery("#"+partid).getGridParam('reccount');
	return noOfRows;
}

function getCellData(partid, rowid, colid)
{
	var cellData=jQuery("#"+partid).getCell(rowid, colid);
	return cellData;
}


function getSelectedTreeNode(variable)
{
	var dtnode = $("#"+variable+"").dynatree("getSelectedNode");		
	return dtnode;		
}


function validateform(variable)
{
	var i='validator'+variable;
	var c =eval(i+'.form()');	
	return c;		
}


function CallInterface(IID)
{
//	location.href='./interfaceenginev2.PortalServlet?IID='+IID;
	if (parent)
		parent.location.href='./PortalServlet?IID='+IID;
	else
		location.href='./PortalServlet?IID='+IID;  //Changed by Diptendu 06-April-2019 
}

function setWindowInterface(IID,part_id)
{
	document.getElementById(part_id).src='../servlet/interfaceenginev2.PortalServlet?IID='+IID;
}




function functionhover(id)
{
	jQuery("#"+id).hover(
			function () 
	      {
				$("#"+id).css('text-decoration','underline');
			},
			
			function () 
			{
				$("#"+id).css('text-decoration','underline');
			}
	);

}

//DONE BY PARTHA
// function resetGrid(interfaceid,partid)
// {
// 	
// 	var page_num = '1';
// 	var sidx_num = jQuery("#"+partid).getGridParam("sortname");
// 	
// 	var sord_num = jQuery("#"+partid).getGridParam("sortorder");
// 
// 	var search_string = "false";
// 	var row_num = jQuery("#"+partid).getGridParam("rowNum");
// 	
// 	var url = './interfaceenginev2.xmlcreator';
// 	HTTPPost(url,{interface_id:interfaceid,part_id:partid,_search:search_string,rows:row_num,page:page_num,sidx:sidx_num,sord:sord_num},function(data){
// 		setGridDataFromXml(partid,data)
// 	},'xml');
// 	 
// }


function getGridPageNoFromSession(interfaceid,partid)
{
	PortalEngine.getFromSessionForReset(interfaceid,partid,'page_no',setPageNoFromReset);
}

function setPageNoFromReset(data)
{
	var page_num;
	var resetdetails=new Array(3);
	resetdetails=data;
	var grid_page_no=resetdetails[2];
	var partid=resetdetails[1];
	var interfaceid=resetdetails[0];
	
	
	if(grid_page_no==null)
	{
		page_num=jQuery("#"+partid).getGridParam("page");
	}
	else
	{
		page_num=grid_page_no;
	}
	var sidx_num = jQuery("#"+partid).getGridParam("sortname");
	var sord_num = jQuery("#"+partid).getGridParam("sortorder");
	var search_string = "false";
	var row_num = jQuery("#"+partid).getGridParam("rowNum");
	var url = './interfaceenginev2.xmlcreator?search='+search_string;
	HTTPPost(url,{interface_id:interfaceid,part_id:partid,_search:search_string,rows:row_num,page:page_num,sidx:sidx_num,sord:sord_num},function(data){
		setGridDataFromXml(partid,data)
	},'xml');
	 
}



function resetGrid(interfaceid,partid)
{
	getGridPageNoFromSession(interfaceid,partid);
}



function HTTPPost(url,postDataArray,callbackfunction,expectedreturntype)
{
	jQuery.post(url,postDataArray,callbackfunction,expectedreturntype);
	
}

function HTTPGet(url,postDataArray,callbackfunction,expectedreturntype)
{
	jQuery.get(url,postDataArray,callbackfunction,expectedreturntype);
	
}


function setGridDataFromXml(gridid,data)
{
		
	jQuery("#"+gridid)[0].addXmlData(data);

}

function setXmlGridData(gridid,data)
{
 	//alert(data);
	//var rowdata=eval(data);
	jQuery("#"+gridid)[0].addXmlData(data);
	setReloadGrid(gridid);
}

function setArrayGridRowData(gridid,content)
{
	var len=content.length;
	alert("111"+len);
	var c=new Array(len);
	c=content;
	var rowdata;
	var counter=1;
	for(u=0;u<c.length;u++)
	{
		alert("555"+counter);
		rowdata=c[u];
		alert("2222"+rowdata);
		//var datastring = eval( "(" + rowdata + ")" );
		//alert("333"+datastring);
		//$("#"+gridid).addRowData("\""+counter+"\"",datastring);
		$("#"+gridid).addRowData(counter,rowdata);
		//$("#"+gridid).jqGrid('addRowData',counter,rowdata);
		alert("444");
		counter++;
	}
	setReloadGrid(gridid);
}

// function setArrayGridData(gridid,len,content)
// {
// 	var c=new Array(len);
// 	c=content;
// 	var rowdata;
// 	rowdata1=c[0];
// 	var myObj = eval( "(" + rowdata1 + ")" );
// 	$("#"+gridid).addRowData("1",myObj);
// }

function setArrayGridData(gridid,col_id,content)
{
	var c=content;
	c=content;
// 	alert(content);
	var rowdata=eval(c);
	$("#"+gridid).addRowData(col_id,rowdata);
	setReloadGrid(gridid);
}

function clearGridData( gridId)
{
	$("#"+gridId).clearGridData();
	//$("#"+gridId).jqGrid("clearGridData");
	setReloadGrid(gridId);
}

function fadeIn( partId )
{
	$("#"+partId).fadeIn("slow");
}

function fadeOut( partId )
{
	$("#"+partId).fadeOut("slow");
}

function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {     

	//If JSONData is not an object then JSON.parse will parse the JSON string in an Object
	var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
	var CSV = '';    
	//This condition will generate the Label/Header
	if (ShowLabel) {
	    var row = "";

	    //This loop will extract the label from 1st index of on array
	    for (var index in arrData.items[0]) {
	        //Now convert each value to string and comma-seprated
	        row += index + ',';
	    }
	    row = row.slice(0, -1);
	    //append Label row with line break
	    CSV += row + '\r\n';
	}

	//1st loop is to extract each row
	for (var i = 0; i < arrData.items.length; i++) {
	    var row = "";
	    //2nd loop will extract each column and convert it in string comma-seprated
	    for (var index in arrData.items[i]) {
	        row += '"' + arrData.items[i][index].replace(/(<([^>]+)>)/ig, '') + '",';
	    }
	    row.slice(0, row.length - 1);
	    //add a line break after each row
	    CSV += row + '\r\n';
	}

	if (CSV == '') {        
	    alert("Invalid data");
	    return;
	}   

	/*
	 * 
	 * FORCE DOWNLOAD
	 * 
	 */
	
	
	//this trick will generate a temp "a" tag
	var link = document.createElement("a");    
	link.id="lnkDwnldLnk";

	//this part will append the anchor tag and remove it after automatic click
	document.body.appendChild(link);

	var csv = CSV;  
	blob = new Blob([csv], { type: 'text/csv' }); 
	
	var myURL = window.URL || window.webkitURL;
	
	var csvUrl = myURL.createObjectURL(blob);
	var filename = 'UserExport.csv';
	jQuery("#lnkDwnldLnk")
	.attr({
	    'download': filename,
	    'href': csvUrl
	}); 

	jQuery('#lnkDwnldLnk')[0].click();    
	document.body.removeChild(link);
	
}

function exportToExcel(interface_id, part_id){
	
	$.ajax({
        type: "GET",
        async: true,
        url: "./interfaceenginev2.GetJSONforGrid",
        data: "interface_id=" + interface_id + "&part_id=" + part_id,

        success: function (data)
        {
        	var colNames = $('#' + part_id).jqGrid ('getGridParam', 'colNames');
        	var colModel = $('#' + part_id).jqGrid ('getGridParam', 'colModel');
			var exportColNames = [];
			var exportColModel = [];

        	var hiddenColumns = [];
        	for( var i = 0; i < colModel.length; i++){ 
        		 if ( colModel[i].hidden === true) {
        			 hiddenColumns.push(colModel[i].name);
               		 //colNames.splice(i, 1); 
               		 //colModel.splice(i, 1);         		   
        		 } 
				 else {
					exportColNames.push(colNames[i]);
					exportColModel.push(colModel[i]);
				 }
        	}
        	
        	for( var i = 0; i < data.length; i++){ 
            	for( var j = 0; j < hiddenColumns.length; j++){
            		var hiddenColumnName = hiddenColumns[j];
            		delete data[i][hiddenColumnName];
            	}
        	}
        	
        	$.unblockUI();

         	
       	    var ws = XLSX.utils.json_to_sheet(data);
        	var range = XLSX.utils.decode_range(ws['!ref']);
         	for(var C = range.s.c; C <= range.e.c; ++C) {
         	  var address = XLSX.utils.encode_col(C) + "1"; 
         	  if(!ws[address]) continue;
         	  ws[address].v = exportColNames[C];
         	}
         	
        	var wscols = [];
        	exportColModel.forEach(function (column) {
        	    wscols.push({wpx:column.width});
        	});
        	ws["!cols"] = wscols;
           	var wb = XLSX.utils.book_new();
           	var sheetName = interface_id.length > 31 ? interface_id.substring(0, 31) : interface_id;
        	XLSX.utils.book_append_sheet(wb, ws, sheetName);
        	XLSX.writeFile(wb, part_id + ".xlsx" );
       },
        error: function (xhr) {
        	$.unblockUI();
        	alert("An error occured: " + xhr.status + " " + xhr.statusText);                
        }
    });
}