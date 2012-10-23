#include "command.h"

namespace afw{

Command::Command()
{
    this->name = NULL;
    this->desc = NULL;
    this->icon = NULL;
}

Command::Command(char* name,char* desc,char* icon)
{
    this->name = name;
    this->desc = desc;
    this->icon = icon;
}

char* Command::getName(){
    return this->name;
}

char* Command::getDescription(){
    return this->desc;
}

char* Command::getIcon(){
    return this->icon;
}

const IResult* Command::exec(void* context){
    return (const IResult*)afw::err_ok;
}

/**
  CommandGroups
*/

CommandGroup::CommandGroup(Command** list,int size){
    this->list=list;
    this->size=size;
}

Command** CommandGroup::getCommandList(){
    return this->list;
}

Command* CommandGroup::getByName(char* name){
    return 0;
}

int CommandGroup::length(){
    return this->size;
}
}
