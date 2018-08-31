<?xml version="1.0"?>
<recipe>
    

    <instantiate from="src/app_package/classes/Activity.java.ftl"
      to="${escapeXmlAttribute(srcOut)}/${activityClass}Activity.java" />


    <instantiate from="src/app_package/classes/ViewModel.java.ftl"
      to="${escapeXmlAttribute(srcOut)}/${activityClass}ViewModel.java" />

    <#if hasModelAdater!false><instantiate from="src/app_package/classes/ModelAdapter.java.ftl"
                                    to="${escapeXmlAttribute(srcOut)}/${activityClass}ModelAdapter.java" /></#if>

    <#include "activity_layout_recipe.xml.ftl" />

</recipe>
