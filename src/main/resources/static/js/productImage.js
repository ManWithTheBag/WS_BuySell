//
//Preview the image in product form
var loadFile = function (event, imageElemId, dbImageElemId){
    setDbImageId(dbImageElemId);

    var reader = new FileReader();
    reader.onload = function (){
        var image = document.getElementById(imageElemId);
        image.src = reader.result;
    }
    reader.readAsDataURL(event.target.files[0]);
}
//
// Delete preview image and set default
function deletePreviewImage(imageElemId, dbImageElemId){
    var imageElem = document.getElementById(imageElemId);
    setDbImageId(dbImageElemId)
    // Set default image
    imageElem.src = "/defaultImages/default_product_image.png";
}
//
// Set Db image id = 0
function setDbImageId(dbImageElemId){
    console.log(dbImageElemId);
    let dbImageElem = document.getElementById(dbImageElemId);
    console.log(dbImageElem)
    dbImageElem.dataset.dbImageId = "0";
}
//
// Refresh <input> file
function refreshInputImageField(inputElemId){
    var inputElem = document.getElementById(inputElemId);
    inputElem.value = null;
}
//
// Check image from DB and show if image exist
const dbImageIdElementList = document.querySelectorAll(".js_data-image-selector");
showDbImage(dbImageIdElementList)
function showDbImage(elemList){
    for (let i = 0; i < elemList.length; i++) {
        if(elemList[i].dataset.dbImageId != null){
            var imageElem = document.getElementById(elemList[i].dataset.imageElemId);
            imageElem.src = "/images/" + elemList[i].dataset.dbImageId;
        }else {
            console.log("not find")
        }
    }
}
//
// Create result image id (after update product)
function createResultImageIdList(){
    for (let i = 0; i < dbImageIdElementList.length; i++) {
        if(dbImageIdElementList[i].dataset.dbImageId != undefined){
            dbImageIdElementList[i].value = parseInt(dbImageIdElementList[i].dataset.dbImageId);
        }else {
            dbImageIdElementList[i].value = 0;
        }
    }
}

