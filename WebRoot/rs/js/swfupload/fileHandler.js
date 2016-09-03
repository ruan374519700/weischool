
function wordFileQueueError(file, errorCode, message) {
	try {
		var imageName = "<font color='red'>文件上传错误</font>";
		var errorName = "";
		//SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED
		if (errorCode == SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			errorName = "改图文的附件数目已达到限制数目,请删减后再进行上传。";
		}
		if (errorName != "") {
			alert(errorName);
			return;
		}
		
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			imageName = "<font color='red'>文件大小为0</font>";
			message = "文件大小为0";
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			imageName = "<font color='red'>文件大小超过限制</font>";
			message = "文件大小超过5MB";
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			message = "请不要上传允许外的文件";
			break;
		default:
			break;
		}
		alert(message);
		addReadyFileInfo(file.id,file.name,"无法上传");

	} catch (ex) {
		this.debug(ex);
	}
}

function wordFileQueued(file){
	setUploadNumber();
	if (count >= 5){
//		fileQueueError(file, SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED, "shumuguoduo");
	} else {
		addReadyFileInfo(file.id,file.name);
	}
}
function wordFileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesQueued > 0) {
			document.getElementById('btnCancel').disabled = "";
			startUpload();
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function wordUploadProgress(file, bytesLoaded) {
	try {
		var percent = Math.ceil((bytesLoaded / file.size) * 100);

		var progress = new FileProgress(file,  this.customSettings.upload_target);
		progress.setProgress(percent);
		if (percent === 100) {
			progress.setStatus("");//正在创建缩略图...
			progress.toggleCancel(false, this);
		} else {
			progress.setStatus("正在上传...");
			progress.toggleCancel(true, this);
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function wordUploadError(file, errorCode, message) {
	var imageName =  "<font color='red'>文件上传出错!</font>";
	var progress;
	try {
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			try {
				progress = new FileProgress(file,  this.customSettings.upload_target);
				progress.setCancelled();
				progress.setStatus("<font color='red'>取消上传!</font>");
				progress.toggleCancel(false);
			}
			catch (ex1) {
				this.debug(ex1);
			}
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			try {
				progress = new FileProgress(file,  this.customSettings.upload_target);
				progress.setCancelled();
				progress.setStatus("<font color='red'>停止上传!</font>");
				progress.toggleCancel(true);
			}
			catch (ex2) {
				this.debug(ex2);
			}
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			imageName = "<font color='red'>文件大小超过限制!</font>";
			break;
		default:
			alert(message);
			break;
		}
		addFileInfo(file.id,imageName);
	} catch (ex3) {
		this.debug(ex3);
	}

}

function wordUploadSuccess(file, serverData) {
	try {  
        var result = serverData.split('|');  
        if(result[0] != 'success') {  
            var progress = new FileProgress(file, this.customSettings.progressTarget);  
            progress.setError();  
            progress.setStatus(serverData);  
            progress.toggleCancel(false);  
        } else {  
            var progress = new FileProgress(file, this.customSettings.progressTarget);  
            progress.setComplete();  
            progress.setStatus("上传完成");  
            progress.toggleCancel(false); 
            var path = result[1].split("ROOT");
            addFile(file.name,path[1]);
            deleteFile(file.id);
            saveFile();
        }  
  
    } catch (ex) {  
        this.debug(ex);  
    }
}

function wordUploadComplete(file) {
	try {
		/*  I want the next upload to continue automatically so I'll call startUpload here */
		if (this.getStats().files_queued > 0) {
			this.startUpload();
		} else {
			var progress = new FileProgress(file,  this.customSettings.upload_target);
			progress.setComplete();
			//progress.setStatus("<font color='red'>所有文件上传完毕!</b></font>");
			progress.toggleCancel(false);
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function queueComplete (numFilesUploaded){
	window.alert("queueComplete = "+numFilesUploaded);
}

function addFileInfo(fileId,message){
	var row = document.getElementById(fileId);
	row.cells[2].innerHTML = "<font color='green'>"+message+"</font>";
}

function setUploadNumber (){
	var stats = wordSwfu.getStats();
	stats.successful_uploads = count;
	wordSwfu.setStats(stats);
}

function addReadyFileInfo(fileid,fileName,status){
	//用表格显示
	var html = "<tr id='"+fileid+"'>";
	html += "<td>"+fileName+"</td>";
	if (status!=null&&status!=""){
		html += "<td><font color='red'>"+status+"</font></td>";
	} else {
		html += "<td></td>";
	}
	html += "<td><a href='javascript:deleteFile(\""+fileid+"\")'>删除</a></td>";
	$("#infoTable").append(html);
	
}

function cancelUpload(){
	var infoTable = document.getElementById("infoTable");
	var rows = infoTable.rows;
	var ids = new Array();
	if(rows==null){
		return false;
	}
	for(var i=0;i<rows.length;i++){
		ids[i] = rows[i].id;
	}	
	for(var i=0;i<ids.length;i++){
		deleteFile(ids[i]);
	}	
}

function deleteFile(fileId){
	//用表格显示
	var infoTable = document.getElementById("infoTable");
	var row = document.getElementById(fileId);
	infoTable.deleteRow(row.rowIndex);
	wordSwfu.cancelUpload(fileId,false);
}

function delDiv(mydiv) {  
    mydiv.parentNode.removeChild(mydiv);  
    //swfu参见iframe页面中的 swfu = new SWFUpload(settings);  
    var stats = swfu.getStats();  
    stats.successful_uploads--;  
    swfu.setStats(stats);  
    var status = document.getElementById("divStatus");  
    status.innerHTML = "已上传 " + stats.successful_uploads + " 个文件,还可以上传"+ parseInt(swfu.settings['file_upload_limit']-stats.successful_uploads) +"个文件";  
}

function addFile (fileName,path) {
	$("#fileName").val(fileName);
	$("#filePath").val(path);
}

function addUploadedFile(fileid,filename){
	var html = "<tr>";
	html+="<td>"+filename+"</td>";
	html+="<td><a href='#' class='"+fileName+"' id='"+fileid+"' onclick=removeExistFile(this)>删除</a></td>";
	html+="</tr>";
	$("#existTable").append(html);
}

function FileProgress(file, targetID) {
	this.fileProgressID = "divFileProgress";

	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "progressWrapper";
		this.fileProgressWrapper.id = this.fileProgressID;

		this.fileProgressElement = document.createElement("div");
		this.fileProgressElement.className = "progressContainer";

		var progressCancel = document.createElement("a");
		progressCancel.className = "progressCancel";
		progressCancel.href = "#";
		progressCancel.style.visibility = "hidden";
		progressCancel.appendChild(document.createTextNode(" "));

		var progressText = document.createElement("div");
		progressText.className = "progressName";
		progressText.appendChild(document.createTextNode("上传文件: "+file.name));

		var progressBar = document.createElement("div");
		progressBar.className = "progressBarInProgress";

		var progressStatus = document.createElement("div");
		progressStatus.className = "progressBarStatus";
		progressStatus.innerHTML = "&nbsp;";

		this.fileProgressElement.appendChild(progressCancel);
		this.fileProgressElement.appendChild(progressText);
		this.fileProgressElement.appendChild(progressStatus);
		this.fileProgressElement.appendChild(progressBar);

		this.fileProgressWrapper.appendChild(this.fileProgressElement);
		document.getElementById(targetID).style.height = "75px";
		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
		fadeIn(this.fileProgressWrapper, 0);

	} else {
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
		this.fileProgressElement.childNodes[1].firstChild.nodeValue = "上传文件: "+file.name;
	}

	this.height = this.fileProgressWrapper.offsetHeight;

}
FileProgress.prototype.setProgress = function (percentage) {
	this.fileProgressElement.className = "progressContainer green";
	this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
	this.fileProgressElement.childNodes[3].style.width = percentage + "%";
};
FileProgress.prototype.setComplete = function () {
	this.fileProgressElement.className = "progressContainer blue";
	this.fileProgressElement.childNodes[3].className = "progressBarComplete";
	this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setError = function () {
	this.fileProgressElement.className = "progressContainer red";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setCancelled = function () {
	this.fileProgressElement.className = "progressContainer";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setStatus = function (status) {
	this.fileProgressElement.childNodes[2].innerHTML = status;
};

FileProgress.prototype.toggleCancel = function (show, swfuploadInstance) {
	this.fileProgressElement.childNodes[0].style.visibility = show ? "visible" : "hidden";
	if (swfuploadInstance) {
		var fileID = this.fileProgressID;
		this.fileProgressElement.childNodes[0].onclick = function () {
			swfuploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};