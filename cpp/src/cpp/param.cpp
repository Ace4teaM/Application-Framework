#include "param.h"

namespace afw{

Param::Param(char* title)
{
    this->title=title;
}

const char* Param::getTitle(){
    return this->title;
}

const char* Param::get(const char* name)
{
    //return this->fields[name];
    return this->fields[name].c_str();
}

const char* Param::set(const char* name,const char* value)
{   this->fields[name] = value;
    return value;
}
}
