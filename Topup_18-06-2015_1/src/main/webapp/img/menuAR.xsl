<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="2.0">
    
    <xsl:template match="Categories">
        <ul>
            <xsl:attribute name="id">Menu1</xsl:attribute>
            <xsl:attribute name="class">MM</xsl:attribute>
            <xsl:apply-templates/>
        </ul>    
    </xsl:template>
    
    <xsl:template match="CATEGORY_LAST">
        <li>
            <xsl:element name="a">
                <xsl:attribute name="href">
                    <xsl:value-of select="HREF"/>
                </xsl:attribute>
                <xsl:value-of select="AR_CATEGORY"/>
            </xsl:element>

            <xsl:if test="SUBCATLIST/CATEGORY_T">
                <xsl:for-each select="SUBCATLIST">
                    <ul> 
                        <xsl:for-each 
                            select="CATEGORY_T"> 
                            <xsl:choose>
                                <xsl:when test="SUBCATLIST/CAT_T">
                                    <li>     
                                        <xsl:element name="a">
                                            <xsl:attribute name="href">
                                        <xsl:value-of  select="HREF"/>                                                                        
                                            </xsl:attribute>
                                            <xsl:value-of     select="AR_CATEGORY"/>
                                        </xsl:element>                           
                                        <ul>                                         
                                            <xsl:for-each 
                                                select="SUBCATLIST/CAT_T">
                                                <xsl:choose>
                                                    <xsl:when test="SUBCATLIST/SUBCAT_T"> 
                                                        <li>                      
                                                            <xsl:element name="a">
                                                                <xsl:attribute name="href">
                                                                      <xsl:value-of  select="HREF"/>     
                                                                </xsl:attribute>
                                                                <xsl:value-of 
                                                                    select="AR_CATEGORY"/>
                                                            </xsl:element>
                                                            <ul>                                         
                                                                <xsl:for-each 
                                                                    select="SUBCATLIST/SUBCAT_T">
                                                                    <li>                      
                                                                        <xsl:element name="a">
                                                                            <xsl:attribute name="href">
                                                                           <xsl:value-of  select="HREF"/>  
                                                                            </xsl:attribute>
                                                                            <xsl:value-of 
                                                                                select="AR_CATEGORY"/>
                                                                        </xsl:element>
                                                                    </li> 
                                                                </xsl:for-each>
                                                            </ul>
                                                        </li>                                                                                                                              
                                                    </xsl:when> 
                                                    <xsl:otherwise>
                                                        <li>                      
                                                            <xsl:element name="a">
                                                                <xsl:attribute name="href">
                                                                      <xsl:value-of  select="HREF"/>  
                                                                </xsl:attribute>
                                                                <xsl:value-of 
                                                                    select="AR_CATEGORY"/>
                                                            </xsl:element>
                                                        </li>
                                                    </xsl:otherwise>
                                                </xsl:choose>                                
                                            </xsl:for-each>
                                        </ul>
                                    </li>
                                </xsl:when>
                                <xsl:otherwise>
                                    <li>                      
                                        <xsl:element name="a">
                                            <xsl:attribute name="href">
                                                 <xsl:value-of  select="HREF"/>  
                                            </xsl:attribute>
                                            <xsl:value-of 
                                                select="AR_CATEGORY"/>
                                        </xsl:element>
                                    </li>
                                </xsl:otherwise>
                            </xsl:choose>  
                        </xsl:for-each>
                    </ul>
                </xsl:for-each>
            </xsl:if>
        </li>
    </xsl:template>
</xsl:stylesheet>