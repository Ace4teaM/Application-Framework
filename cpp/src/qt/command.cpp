#include "command.h"

namespace qafw{

    QCommand::QCommand()
    {
    }

    QCommand::QCommand(QString name,QString desc,QIcon icon)
    {
        this->name = name;
        this->desc = desc;
        this->icon = icon;
    }

    QCommand::QCommand(QString name,QString desc)
    {
        this->name = name;
        this->desc = desc;
    }

    QString QCommand::getName(){
        return this->name;
    }

    QString QCommand::getDescription(){
        return this->desc;
    }

    QIcon QCommand::getIcon(){
        return this->icon;
    }

    const QResult* QCommand::exec(QObject* context){
        return new QResult(0,"ok");
    }

}
