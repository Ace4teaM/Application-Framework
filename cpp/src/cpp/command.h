#ifndef COMMAND_H
#define COMMAND_H

#include "../iface/icommand.h"
#include "result.h"

namespace afw{

    class Command : ICommand<char*,char*,void*>
    {
    protected:
        char* name;
        char* desc;
        char* icon;
    public:
        Command();
        Command(char* name,char* desc,char* icon);
        char* getName();
        char* getDescription();
        char* getIcon();
        const IResult* exec(void* context);
    };

    class CommandGroup : ICommandGroup<Command,char*>
    {
    protected:
        Command** list;
        int size;
    public:
        CommandGroup(Command** list,int size);
        Command** getCommandList();
        Command* getByName(char* name);
        int length();
    };

}
#endif // COMMAND_H
