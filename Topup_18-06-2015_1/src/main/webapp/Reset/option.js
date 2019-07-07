var selectedList;
var availableList;
function createListObjects(){
    availableList = document.getElementById("availableOptions");
    selectedList = document.getElementById("selectedOptions");
}
function delAttribute(){
    var selIndex = selectedList.selectedIndex;
    if(selIndex < 0)
        return;
    availableList.appendChild(selectedList.options.item(selIndex))
//    selectNone(selectedList,availableList);
//    setSize(availableList,selectedList);
}
function addAttribute(){
    var addIndex = availableList.selectedIndex;
    if(addIndex < 0)
        return;
    selectedList.appendChild(availableList.options.item(addIndex));
//    selectNone(selectedList,availableList);
//    setSize(selectedList,availableList);
}
function delAll(){
    var len = selectedList.length -1;
    for(i=len; i>=0; i--){
        availableList.appendChild(selectedList.item(i));
    }
//    selectNone(selectedList,availableList);
//    setSize(selectedList,availableList);

}
function addAll(){
    var len = availableList.length -1;
    for(i=len; i>=0; i--){
        selectedList.appendChild(availableList.item(i));
    }
//    selectNone(selectedList,availableList);
//    setSize(selectedList,availableList);
}
function selectNone(list1,list2){
    list1.selectedIndex = -1;
    list2.selectedIndex = -1;
    addIndex = -1;
    selIndex = -1;
}
function setSize(list1,list2){
    list1.size = getSize(list1);
    list2.size = getSize(list2);
}
function getSize(list){
    /* Mozilla ignores whitespace, IE doesn't - count the elements in the list */
    var len = list.childNodes.length;
    var nsLen = 0;
    //nodeType returns 1 for elements
    for(i=0; i<len; i++){
        if(list.childNodes.item(i).nodeType==1)
            nsLen++;
    }
    if(nsLen<2)
        return 2;
    else
        return nsLen;
}
function showSelected(){
    var optionList = document.getElementById("selectedOptions").options;
    var data = '';
    var len = optionList.length;
    for(i=0; i<len; i++){
        if(i>0)
            data += ',';
        data += optionList.item(i).value;
    }
    alert(data);
}


var selectedServicesList;
var availableServicesList;
function createListObjects1(){
    availableServicesList = document.getElementById("availableServiceOptions");
    selectedServicesList = document.getElementById("selectedServiceOptions");
}
function delAttribute1(){
    var selIndex = selectedServicesList.selectedIndex;
    if(selIndex < 0)
        return;
    availableServicesList.appendChild(selectedServicesList.options.item(selIndex))
//    selectNone(selectedList,availableList);
//    setSize(availableList,selectedList);
}
function addAttribute1(){
    var addIndex = availableServicesList.selectedIndex;
    if(addIndex < 0)
        return;
    selectedServicesList.appendChild(availableServicesList.options.item(addIndex));
//    selectNone(selectedList,availableList);
//    setSize(selectedList,availableList);
}
function delAll1(){
    var len = selectedServicesList.length -1;
    for(i=len; i>=0; i--){
        availableServicesList.appendChild(selectedServicesList.item(i));
    }
//    selectNone(selectedList,availableList);
//    setSize(selectedList,availableList);

}
function addAll1(){
    var len = availableServicesList.length -1;
    for(i=len; i>=0; i--){
        selectedServicesList.appendChild(availableServicesList.item(i));
    }
//    selectNone(selectedList,availableList);
//    setSize(selectedList,availableList);
}
function selectNone1(list1,list2){
    list1.selectedIndex = -1;
    list2.selectedIndex = -1;
    addIndex = -1;
    selIndex = -1;
}
function setSize1(list1,list2){
    list1.size = getSize(list1);
    list2.size = getSize(list2);
}
function getSize1(list){
    /* Mozilla ignores whitespace, IE doesn't - count the elements in the list */
    var len = list.childNodes.length;
    var nsLen = 0;
    //nodeType returns 1 for elements
    for(i=0; i<len; i++){
        if(list.childNodes.item(i).nodeType==1)
            nsLen++;
    }
    if(nsLen<2)
        return 2;
    else
        return nsLen;
}
function showSelected1(){
    var optionList = document.getElementById("selectedServiceOptions").options;
    var data = '';
    var len = optionList.length;
    for(i=0; i<len; i++){
        if(i>0)
            data += ',';
        data += optionList.item(i).value;
    }
    alert(data);
}
